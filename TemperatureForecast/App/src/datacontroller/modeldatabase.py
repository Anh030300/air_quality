import numpy as np
import pandas as pd
import datetime

class ModelDatabase:
    def __init__(self,filename):
        self.features = ['temp', 'day_cos', 'day_sin', 'month_sin', 'month_cos', 'humidity']        
        df = pd.read_csv(filename)
        self.hist_data = self.dataframe_to_array_features(df,self.features)
        self.times = [x for x in df["time"]]
        self.MAX_SIZE_HIST_DATA = 200

    def dataframe_to_array_features(self,df:pd.DataFrame,features:list)->np.array:
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
        return np.array(df[features])

    def handle_dict_data(self,new_data:dict)->np.array:
        new_data["time"] = [new_data["time"]]
        new_data["temp"] = map(float,[new_data["temp"]])
        new_data["humidity"] = map(float,[new_data["humidity"]])
        print("Dict Data:")
        print(new_data)
        print("Handling")
        df = pd.DataFrame.from_dict(new_data)
        return self.dataframe_to_array_features(df,self.features)

    def add_data(self,dict_data:dict):
        self.times.append(datetime.datetime.strptime(dict_data["time"], '%Y-%m-%d %H:%M:%S'))
        array_data = self.handle_dict_data(dict_data)
        self.hist_data = np.vstack([self.hist_data,array_data])
        n_rows, n_cols = self.hist_data.shape
        if n_rows>self.MAX_SIZE_HIST_DATA:
            self.hist_data = self.hist_data[-self.MAX_SIZE_HIST_DATA:]
            self.times = self.times[-self.MAX_SIZE_HIST_DATA:]

    def get_input_for_model(self,lag=48)->np.array:
        n_features = self.hist_data.shape[1]
        
        # Creating placeholder lists
        X= []
        X.append(self.hist_data[-lag:])

        X= np.array(X)

        # Reshaping the X array to an RNN input shape 
        X = np.reshape(X, (X.shape[0], lag, n_features))

        return X

    def get_time_for_predicted_val(self,n_ahead):
        predicted_times = []
        latest_time = self.times[-1]
        for i in range(n_ahead):
            predicted_times.append(str(latest_time+datetime.timedelta(minutes = 30*(i+1))))
        return predicted_times