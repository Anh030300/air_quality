from datacontroller.modeldatabase import ModelDatabase
from models.temperaturemodels.TemperatureModel import TemperatureModel
from models.temperaturemodels.SingleStepTemperatureModel import SingleStepTemperatureModel
from models.humiditymodels.HumidityModel import HumidityModel
from models.humiditymodels.SingleStepHumidityModel import SingleStepHumidityModel

import numpy as np
import json

from flask import Flask, request, redirect, url_for
from flask_ngrok import run_with_ngrok
from pyngrok import ngrok
app = Flask(__name__)
run_with_ngrok(app)   
  
@app.route("/")
def home():
    return "<h1>Air Quality Prediction</h1>"
    
['temp', 'day_cos', 'day_sin', 'month_sin', 'month_cos', 'humidity']            
def html_table(table:np.array,times: list):
    yield '<html>\
            <head>\
            <style>\
            table, th, td {\
              border: 1px solid black;\
              border-collapse: collapse;\
            }\
            </style>\
            </head>\
            <body>\
              <table>\
                <tr>\
                  <th>Time</th>\
                  <th>Temperature</th> \
                  <th>DayCos</th>\
                  <th>DaySin</th>\
                  <th>MonthSin</th>\
                  <th>MonthCos</th>\
                  <th>Humidity</th>\
                </tr>'
    for time,row in zip(times,table):
      yield '  <tr><td>'
      yield '    </td><td>'.join(map(str,[time]))
      yield '    </td><td>'
      yield '    </td><td>'.join(map(str,row.tolist()))
      yield '  </td></tr>'
    yield '</table>\
          </body>\
        </html>'    

@app.route("/history")
def get_hist_data():
    table_data = model_database.hist_data
    times = model_database.times
    return "".join(html_table(table_data,times))

@app.route("/publish",methods = ["POST"])
def update_hist_data():
    json_data = request.get_json()
    # dict_data=json.loads(json_data)
    model_database.add_data(json_data)
    return redirect(url_for('get_hist_data'))

@app.route("/multistep")
def multi_step_predict():
    n_ahead = 24
    predicted_times = model_database.get_time_for_predicted_val(n_ahead)

    data = {}
    data["predictions"]=[]

    temp_list = temperature_model.predict()[0]
    humidity_list = humidity_model.predict()[0]

    for i in range(n_ahead):
        data["predictions"].append({"time":predicted_times[i],
                                    "temperature":str(temp_list[i]),
                                    "humidity":str(humidity_list[i])})
    print(data)
    return data

@app.route("/singlestep")
def single_step_predict():
    data = {}
    data["predictions"]=[]
    data["predictions"].append({"time":model_database.get_time_for_predicted_val(1)[0],
                                "temperature":str(single_step_temperature_model.predict()[0][0]),
                                "humidity":str(single_step_humidity_model.predict()[0][0])})
    print(data)
    return data

model_database = ModelDatabase("mockdata/mockdata.csv")

temperature_model = TemperatureModel(model_database)
single_step_temperature_model = SingleStepTemperatureModel(model_database)
humidity_model = HumidityModel(model_database)
single_step_humidity_model = SingleStepHumidityModel(model_database)

def main():
    app.run()
    # mul_temp = temperature_model.predict()
    # sing_temp = single_step_temperature_model.predict()
    # mul_hum = humidity_model.predict()
    # sing_hum = single_step_humidity_model.predict()
    # print("Multi Temp")
    # print(mul_temp)
    # print("==========")
    # print("Sing Temp")
    # print(sing_temp)
    # print("==========")
    # print("Multi Hum")
    # print(mul_hum)
    # print("==========")
    # print("Sing Hum")
    # print(sing_hum)
    # print("==========")


if __name__ == "__main__":
    main()
    