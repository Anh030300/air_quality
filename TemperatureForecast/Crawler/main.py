from NoiBaiHourlyWeatherCrawler import NoiBaiHourlyWeatherCrawler
import pandas as pd
import argparse

def save_to_file(weather_data:list):
    filename = "data/weather.csv"
    df = pd.DataFrame.from_dict(weather_data)
    df.to_csv(filename,mode="a",header=False)


if __name__=="__main__":
    # create parser
    print("Parsing Args")
    parser = argparse.ArgumentParser()
    parser.add_argument("year")
    args = parser.parse_args()
 
    print("Start crawling weather data in {year}".format(year=args.year))
    year = int(args.year)
    crawler = NoiBaiHourlyWeatherCrawler(year) 
    weather_data = crawler.get_year_data()
    print("Done Crawling.\nStart Saving")
    save_to_file(weather_data)
    print("Done Saving")
    