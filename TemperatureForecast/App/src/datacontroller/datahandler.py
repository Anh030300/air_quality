import pandas as pd
import numpy as np
import datetime
def csv_to_array_features(filename:str,features_final:list)->np.array:
    df = pd.read_csv(filename)

    # Converting the dt column to datetime object 
    df['time'] = [datetime.datetime.strptime(x, '%Y-%m-%d %H:%M:%S') for x in df['time']]

    # Sorting by the date 
    df.sort_values('time', inplace=True)
    # Extracting the hour of day
    df['hour'] = [x.hour+x.minute/60 for x in df['time']]

    # Creating the cyclical daily feature 
    df['day_cos'] = [np.cos(x * (2 * np.pi / 24)) for x in df['hour']]
    df['day_sin'] = [np.sin(x * (2 * np.pi / 24)) for x in df['hour']]

    # Extracting the timestamp from the datetime object 
    df['timestamp'] = [x.timestamp() for x in df['time']]

    # Seconds in day 
    s = 24 * 60 * 60 

    # Seconds in year 
    year = (365.25) * s

    df['month_cos'] = [np.cos((x) * (2 * np.pi / year)) for x in df['timestamp']]
    df['month_sin'] = [np.sin((x) * (2 * np.pi / year)) for x in df['timestamp']]
    # print(df[features_final].tail(48))
    return np.array(df[features_final])