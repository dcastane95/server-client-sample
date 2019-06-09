import json
import csv
import requests
import sys

# default parameters and global declarations
csv_path = "sample.csv"
host = "http://localhost:8080"
api_path = "/api/operationAPI"
csv_delimiter = ','


def post_call(json_data):
    return requests.post(host+api_path, json=json_data)


def print_result(result):
    # if the status_code is 200 then all was ok
    if result.status_code == 200:
        if {"result", "firstNumber", "secondNumber", "operator"}.issubset(result.json().keys()):
            json = result.json()
            print("%d %s %d = %.2f" % (json["firstNumber"], json["operator"], json["secondNumber"], json["result"]))
        else:
            print("Error: Unexpected result")
    else:
        # if not, then it will return an error if: missing arguments, a wrong operator or it was trying to divide by 0
        # if no json is returned, result.text is '' and result.json() throws and exception.
        if result.text and "error" in result.json().keys():
            print("Error: " + result.json()["error"])
        # otherwise, it might be a server error or data was not sent properly
        else:
            print("Error: Bad request. Error code received from server: %d" % result.status_code)


arguments = sys.argv[1:]
if len(arguments) == 1:
    csv_path = arguments[0]
    print("Using csv path file: %s" % csv_path)
else:
    print("Using default csv path file: %s. Use %s \"your_path\" to use another path.\n" % (csv_path,sys.argv[0]))

try:
    with open(csv_path) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=csv_delimiter)
        row_count = 0
        for row in csv_reader:
            print("Row number: %d" % row_count)
            if len(row) == 3:
                data = {}
                try:
                    # preparation of data that is going to be sent
                    data["firstNumber"] = int(row[0])
                    data["operator"] = row[1]
                    data["secondNumber"] = int(row[2])

                    # post call to server
                    result = post_call(data)

                    # processing of call result
                    print_result(result)
                except Exception as e:
                    print("Error casting data. Column types should be \"<integer>,<string>,<integer>\" in this order.")
            else:
                print("Error: Wrong format row; there should be 3 values in each row.")
            print("----------------")
            row_count += 1
except Exception as e:
    print("Error opening csv file\n")
