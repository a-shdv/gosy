document.getElementById("get_weather")
.addEventListener("click", ()=>{
    fetch("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m")
        .then(data=>data.json())
        .then(data =>{
            console.log(data["hourly"]["temperature_2m"]);
            const div = document.getElementById("table_with_info");
            const list = document.createElement("ul");
            
            const tempr = data["hourly"]["temperature_2m"];
            for(let i = 0; i < tempr.length; i++){
                const element = document.createElement("li");
                element.innerHTML = tempr[i];
                if(tempr[i] > 20){
                    element.classList.add("hot");
                }
                list.appendChild(element);
            }
            div.appendChild(list);
        });
});