import argparse
import requests, re, typing
from bs4 import BeautifulSoup as soup
import contextlib
import json

import sys
sys.path.insert(0,'/usr/lib/chromium-browser/chromedriver')
from selenium import webdriver
chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('--headless')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--disable-dev-shm-usage')
wd = webdriver.Chrome('chromedriver',chrome_options=chrome_options)

def _remove(d:list) -> list:
   return list(filter(None, [re.sub('\xa0', '', b) for b in d]))

@contextlib.contextmanager
def get_weather_data(url:str, by_url = True) -> typing.Generator[dict, None, None]:
   d = soup(requests.get(url).text if by_url else url, 'html.parser')
   _table = d.find('table', {'id':'wt-his'})
   _data = [[[i.text for i in c.find_all('th')], *[i.text for i in c.find_all('td')]] for c in _table.find_all('tr')]
   [h1], [h2], *data, _ = _data
   _h2 = _remove(h2)
   yield {tuple(_remove(h1)):[dict(zip(_h2, _remove([a, *i]))) for [[a], *i] in data]}

def crawl_weather_by_month_year(wd, month:int,year:int)->dict:
    base_url = "https://www.timeanddate.com/weather/vietnam/hanoi/historic?month={month}&year={year}".format(month=month,year=year)
    wd.get(base_url)
    crawled_data = {}
    for i in wd.find_element_by_id('wt-his-select').find_elements_by_tag_name('option'):
        i.click()
        with get_weather_data(wd.page_source, False) as weather:
            crawled_data[i.text] = weather
    return crawled_data

def fahrenheit_to_celsius(temp)->int:
    return int((temp - 32) / 1.8)

def extract_crawled_data(crawled_data:dict)->dict:
    extracted_data = {}
    for day, day_data in crawled_data.items():
        weather_data=day_data[('Conditions','Comfort')]
        temp_humid = []
        for hourly_weather in weather_data:
            temp_data = hourly_weather['Temp']
            temperature = int(temp_data[:-2])
            if (temp_data[-1]=="F"):
                temperature = fahrenheit_to_celsius(temperature)
            humid = int(hourly_weather['Barometer'][:-1])
            temp_humid.append({"temperature":temperature, "humidity":humid,"time":hourly_weather['Time']})
        temp_humid[0]['time']="12:00 am"
        extracted_data[day]=temp_humid
    return extracted_data    

def save_to_file(extracted_data:dict, month:int, year:int):
    filename = "weather_{month}_{year}".format(month=month,year=year)
    with open(filename,"w") as f:
        json.dump(extracted_data,f,indent=2,ensure_ascii=False)


if __name__=="__main__":
    # create parser
    print("Parsing Args")
    parser = argparse.ArgumentParser()
    parser.add_argument("month")
    parser.add_argument("year")
    args = parser.parse_args()
 
    print("Start crawling weather data in {month}/{year}".format(month=args.month,year=args.year))
    month,year = int(args.month), int(args.year)
    crawled_data = crawl_weather_by_month_year(wd,month,year)
    extracted_data = extract_crawled_data(crawled_data)
    save_to_file(extracted_data,month,year)
    print("Done Crawling")