from models.model import AbstractModel
from datacontroller.modeldatabase import ModelDatabase

import numpy as np
from tensorflow import keras
from keras.models import Input, Model, Sequential


class SingleStepTemperatureModel(AbstractModel):
    def __init__(self,database:ModelDatabase):
        super().__init__(database)
        self.lag = 48

    def load_model(self)->Model:
        return keras.models.load_model("models/singlesteptemperatureforecastmodel")
    
    # def create_inp_predict(self)->np.array:
    #     n_features = self.hist_data.shape[1]
    #     inp = []
    #     inp.append(self.hist_data[-self.lag:])
    #     inp = np.array(inp)
    #     inp = np.reshape(inp,(inp.shape[0],self.lag,n_features))
    #     return inp
    def create_inp_predict(self)->np.array:
        return self.database.get_input_for_model(self.lag)