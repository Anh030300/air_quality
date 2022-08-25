from AbstractHourlyWeatherCrawler import AbstractHourlyWeatherCrawler
from bs4 import BeautifulSoup
import requests
import json
import itertools
class NoiBaiHourlyWeatherCrawler(AbstractHourlyWeatherCrawler):

    def __init__(self,year):
        super().__init__(year)
        self.time_range=self.get_time_observation_range()

    def get_time_observation_range(self)->list:
        time_range=[]
        mins = ["00","30"]
        for i in range(24):
            for min in mins:
                time_range.append(f"{i:02d}:{min:s}:00")
        return time_range
        
    def get_location_url(self)->str:
        return self.base_url.format(location="VVNB:9:VN",date="{date}")
    
    def get_hourly_data(self,location_url,day_num)->list:
        day = self.get_date_from_day_num(day_num,"%Y%m%d")
        str_key_date = self.get_date_from_day_num(day_num,"%Y-%m-%d")
        api_url = location_url.format(date=day)
        web = requests.get(api_url)
        soup = BeautifulSoup(web.content, "html.parser")
        json_soup = json.loads(soup.get_text())
        observations=[]
        for ele,time in itertools.zip_longest(json_soup["observations"],self.time_range):
            try :
                observations.append({"time":(str_key_date+" "+time),"temp":ele["temp"],"humidity":ele["rh"]})
            except :
                observations.append({"time":(str_key_date+" "+time),"temp":None,"humidity":None})
        return observations