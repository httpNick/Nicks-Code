import urllib.request
import json
import pickle
import time

def k_to_f(num):
	num = (num - 273.15)*1.8
	return round(num + 32, 2)

def get_info():

	city = input("What city would you like the weather for? ")
	url = 'http://api.openweathermap.org/data/2.5/find?q=' + city + '&mode=json'
	data = urllib.request.urlopen(url)
	data = data.read()
	json_info = json.loads(data.decode('UTF-8'))
	return json_info

def manage_file(current_city):
	read_f = open('temp.txt', 'rb')
	dict = pickle.load(read_f)
	read_f.close()

	dict[time.strftime('%Y/%m/%d %H:%M:%S ') + current_city] = current_temp
	write_f = open('temp.txt', 'wb')
	pickle.dump(dict, write_f)
	return dict
	
json_info = get_info()
current_temp = k_to_f(((json_info['list'][0]['main']['temp'])))

current_city = (json_info['list'][0]['name'])

print('Current temperature {0} degrees F in {1} '.format(current_temp, current_city))

map = manage_file(current_city)

