from abc import ABC, abstractmethod
import datetime, calendar
class AbstractHourlyWeatherCrawler(ABC):
    def __init__(self,year):
        self.year=year
        self.APIKey = "e1f10a1e78da46f5b10a1e78da96f525"
        self.base_url = "https://api.weather.com/v1/location/{location}/observations/historical.json?apiKey={apiKey}&units=m&startDate={date}"\
        .format(location="{location}",apiKey=self.APIKey,date="{date}")
        self.year_data={}

    def get_year_data(self)->list:
        location_url = self.get_location_url()
        if not self.year_data:
            self.year_data = []
            n_days = self.get_num_days_of_year()
            for i in range(1,n_days+1):
                # str_date = self.get_date_from_day_num(i,"%Y%m%d")
                # str_key_date = self.get_date_from_day_num(i,"%Y-%m-%d")
                self.year_data.extend(self.get_hourly_data(location_url,i))
        return self.year_data

    @abstractmethod
    def get_location_url(self)->str:
        pass

    @abstractmethod
    def get_hourly_data(self,location_url,day)->list:
        pass

    def get_num_days_of_year(self)->int:
        return ((datetime.date(self.year,12,31)-datetime.date(self.year,1,1)).days+1)

    def get_date_from_day_num(self,day_num,str_format)->str:
        return datetime.datetime.strptime(str(self.year) + "-" + str(day_num), "%Y-%j").strftime(str_format)     