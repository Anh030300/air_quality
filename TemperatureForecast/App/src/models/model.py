from abc import ABC, abstractmethod
import numpy as np
from tensorflow import keras
from keras.models import Model
from datacontroller.modeldatabase import ModelDatabase

class AbstractModel:
    def __init__(self,database:ModelDatabase):
        self.model = self.load_model()
        self.database = database

    @abstractmethod
    def load_model(self)->Model:
        pass

    def predict(self)->np.array:
        input = self.create_inp_predict()
        return self.model.predict(input)

    @abstractmethod
    def create_inp_predict(self)->np.array:
        pass