# WeatherRestApp

Simple application, that registers sensors, takes weather measurements from sensors, displays the number of rainy days

### API

<details><summary><b>Sensor registration:</b></summary><blockquote>

<details><summary>Http:</summary>

```http request
URI: http://localhost:8080/sensors/registration
Method: POST
Content-Type: application/json
```

</details>

<details><summary>Request:</summary>

```json
{
  "name": "Sensor name"
}
```

</details>

<details>
  <summary>Response:</summary>

HttpStatus.CREATED

</details>
</blockquote></details>

<details><summary><b>Delete sensor:</b></summary><blockquote>

<details><summary>Http:</summary>

```http request
URI: http://localhost:8080/sensors
Method: DELETE
Content-Type: application/json
```

</details>

<details><summary>Request:</summary>

```json
{
  "name": "Sensor name"
}
```

</details>

<details>
  <summary>Response:</summary>

HttpStatus.OK

</details>
</blockquote></details>

<details><summary><b>Create measurement:</b></summary><blockquote>

<details><summary>Http:</summary>

```http request
URI: http://localhost:8080/measurements/add
Method: POST
Content-Type: application/json
```

</details>

<details><summary>Request:</summary>

```json
{
  "value": 24.7,
  "raining": false,
  "sensor": {
    "name": "Sensor name"
  }
}
```

</details>

<details>
  <summary>Response:</summary>

HttpStatus.CREATED

</details>
</blockquote></details>

<details><summary><b>Get measurements:</b></summary><blockquote>


<details><summary>Http:</summary>

```http request
URI: http://localhost:8080/measurements
Method: GET
```

</details>

<details>
  <summary>Request:</summary>

```json
{
  "measurements": [
    {
      "value": 11.0,
      "raining": true,
      "sensor": {
        "name": "Sensor name"
      }
    },
    {
      "value": 11.0,
      "raining": true,
      "sensor": {
        "name": "Sensor name"
      }
    }
  ]
}
```

</details>
</blockquote></details>

<details><summary><b>Get rainy days:</b></summary><blockquote>


<details><summary>Http:</summary>

```http request
URI: http://localhost:8080/measurements/rainyDaysCount
Method: GET
```
</details>

<details>
  <summary>Request:</summary>
Integer
</details>

</blockquote></details>
