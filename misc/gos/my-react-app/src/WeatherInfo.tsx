interface Props {
    temp: number;
    date: string;
}

const WeatherInfo = (props: Props) => {
    return <div>
        <div>{props.temp}</div>
        <div>{props.date}</div>
    </div>
}

export default WeatherInfo;
