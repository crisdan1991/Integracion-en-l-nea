import json
from flask import Flask, jsonify, request, abort

app = Flask(__name__)

customer = [{
    "codigo": 1,
    "identificacion": "1722207709",
    "nombre": "Jhony Llano",
    "email": "jllanot@est.ups.edu.ec",
    "channel": 1
}, {
    "codigo": 2,
    "identificacion": "0504121788",
    "nombre": "Olga Changoluisa",
    "email": "ochangoluisa@est.ups.edu.ec",
    "channel": 1
}]


@app.route('/customers', methods=['GET'])
def returnCustomers():
    if (request.method == 'GET'):
        return jsonify(customer)


@app.route('/customer/<int:codigo>', methods=['GET'])
def returnCustomer(codigo):
    if (request.method == 'GET'):
        for person in customer:
            if (person.get("codigo") == codigo):
                return jsonify(person)


@app.route('/customer', methods=['POST'])
def addCustomer():
    if (request.headers.get('Content-Type') == 'application/json'):
        person = request.json
        customer.append(person)
        return "OK"
    else:
        abort(400, 'No valido')


if __name__ == '__main__':
    app.run(debug=True)
