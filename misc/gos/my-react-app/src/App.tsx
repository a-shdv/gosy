import React, { useEffect, useState } from 'react';
import './App.css';
import WeatherInfo from './WeatherInfo';

interface HourlyWeatherData {
  temperature_2m: number[];
  time: string[];
}

interface WeatherDataResponse {
  hourly: HourlyWeatherData;
}

function App() {
  const [data, setData] = useState<WeatherDataResponse>();
  useEffect(() => {
    fetch("https://api.open-meteo.com/v1/forecast?latitude=54.33&longitude=48.39&hourly=temperature_2m")
      .then(d => d.json())
      .then(d => setData(d));
  }, []);
  return (
    <div className="App">
      <header className="App-header">
        {data && data.hourly.time.map((currentTime, index) => {
          return <WeatherInfo key={index} date={currentTime} temp={data.hourly.temperature_2m[index]} />
        })}
      </header>
    </div>
  );
}

export default App;
