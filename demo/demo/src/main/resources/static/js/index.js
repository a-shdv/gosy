document.addEventListener("DOMContentLoaded", () => {
    document
        .getElementById("room-timetable-form")
        .addEventListener("submit", async (event) => {
            event.preventDefault();
            const formData = new FormData(event.target);
            const response = await fetch("https://vm.nathoro.ru/timetable/by-room/" + encodeURIComponent(formData.get("room")));
            const data = await response.json();

            console.log(data);

            const wrapper = document.getElementById("timetable-wrapper");
            renderWeek(data[0], wrapper); // 1 неделя
            renderWeek(data[1], wrapper); // 2 неделя
        });

    document
        .getElementById("fetch-groups-button")
        .addEventListener("click", fetchGroups);

    document
        .getElementById("fetch-rooms-button")
        .addEventListener("click", fetchRooms);

    document
        .getElementById("fetch-teachers-button")
        .addEventListener("click", fetchTeachers);

    document
        .getElementById("group-timetable-form")
        .addEventListener("submit", async (event) => {
            event.preventDefault();
            const formData = new FormData(event.target);
            const response = await fetch("https://vm.nathoro.ru/timetable/by-group/" + encodeURIComponent(formData.get("group")));
            const data = await response.json();

            console.log(data);

            const wrapper = document.getElementById("group-timetable-wrapper");
            renderGroupTimetable(data, wrapper); // Render the group timetable
        });

    document
        .getElementById("teacher-timetable-form")
        .addEventListener("submit", async (event) => {
            event.preventDefault();
            const formData = new FormData(event.target);
            const response = await fetch("https://vm.nathoro.ru/timetable/by-teacher/" + encodeURIComponent(formData.get("teacher")));
            const data = await response.json();

            console.log(data);

            const wrapper = document.getElementById("teacher-timetable-wrapper");
            renderTeacherTimetable(data, wrapper); // Render the teacher's timetable
        });

    document
        .getElementById("weather-form")
        .addEventListener("submit", async (event) => {
            event.preventDefault();
            const formData = new FormData(event.target);
            const latitude = encodeURIComponent(formData.get("lattitude"));
            const longitude = encodeURIComponent(formData.get("longitude"));
            // Пример запроса
            // https://vm.nathoro.ru/weather?lattitude=54.3&longitude=48.4
            const response = await fetch(`https://vm.nathoro.ru/weather?lattitude=${latitude}&longitude=${longitude}`);
            const data = await response.json();

            console.log(data);

            const wrapper = document.getElementById("weather-wrapper");
            renderWeather(data, wrapper); // Render the weather data
        });
});

async function fetchGroups() {
    const response = await fetch("https://vm.nathoro.ru/timetable/groups");
    const data = await response.json();

    console.log(data);

    const wrapper = document.getElementById("groups-wrapper");
    renderGroups(data, wrapper);
}

async function fetchRooms() {
    const response = await fetch("https://vm.nathoro.ru/timetable/rooms");
    const data = await response.json();

    console.log(data);

    const wrapper = document.getElementById("rooms-wrapper");
    renderRooms(data, wrapper);
}

async function fetchTeachers() {
    const response = await fetch("https://vm.nathoro.ru/timetable/teachers");
    const data = await response.json();

    console.log(data);

    const wrapper = document.getElementById("teachers-wrapper");
    renderTeachers(data, wrapper);
}

function renderGroups(data, wrapper) {
    // Clear previous content
    wrapper.innerHTML = "";

    const ul = document.createElement("ul");

    data.forEach(group => {
        const li = document.createElement("li");
        li.textContent = group.name;
        ul.appendChild(li);
    });

    wrapper.appendChild(ul);
}

function renderRooms(data, wrapper) {
    // Clear previous content
    wrapper.innerHTML = "";

    const ul = document.createElement("ul");

    data.forEach(room => {
        const li = document.createElement("li");
        li.textContent = room.name;
        ul.appendChild(li);
    });

    wrapper.appendChild(ul);
}

function renderTeachers(data, wrapper) {
    // Clear previous content
    wrapper.innerHTML = "";

    const ul = document.createElement("ul");

    data.forEach(teacher => {
        const li = document.createElement("li");
        li.textContent = teacher.fullName;
        ul.appendChild(li);
    });

    wrapper.appendChild(ul);
}

function renderWeek(data, wrapper) {
    // Clear previous content
    wrapper.innerHTML = "";

    const table = document.createElement("table");
    table.innerHTML = `
        <tr>
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>5</th>
            <th>6</th>
            <th>7</th>
            <th>8</th>
        </tr>
    `;
    data.days.forEach(day => {
        const week = document.createElement('tr');
        day.lessons.forEach(lesson => {
            if (lesson === null) {
                week.innerHTML += `<td class="slot-empty">----</td>`;
            } else {
                week.innerHTML += `<td class="slot-full">${lesson.subject.name} (${lesson.subject.type})<br>${lesson.room.name}<br>${lesson.subject.teacher.fullName}</td>`;
            }
        });
        table.append(week);
    });
    wrapper.append(table);
}

function renderGroupTimetable(data, wrapper) {
    // Clear previous content
    wrapper.innerHTML = "";

    data.forEach((week, index) => {
        const table = document.createElement("table");
        table.innerHTML = `
            <caption>${week.isOdd ? "Odd Week" : "Even Week"}</caption>
            <tr>
                <th>1</th>
                <th>2</th>
                <th>3</th>
                <th>4</th>
                <th>5</th>
                <th>6</th>
                <th>7</th>
                <th>8</th>
            </tr>
        `;
        week.days.forEach(day => {
            const weekRow = document.createElement('tr');
            day.lessons.forEach(lesson => {
                if (lesson === null) {
                    weekRow.innerHTML += `<td class="slot-empty">----</td>`;
                } else {
                    weekRow.innerHTML += `<td class="slot-full">${lesson.subject.name} (${lesson.subject.type})<br>${lesson.room.name}<br>${lesson.subject.teacher.fullName}</td>`;
                }
            });
            table.append(weekRow);
        });
        wrapper.append(table);
    });
}

function renderTeacherTimetable(data, wrapper) {
    // Clear previous content
    wrapper.innerHTML = "";

    data.forEach((week, index) => {
        const table = document.createElement("table");
        table.innerHTML = `
            <caption>${week.isOdd ? "Odd Week" : "Even Week"}</caption>
            <tr>
                <th>1</th>
                <th>2</th>
                <th>3</th>
                <th>4</th>
                <th>5</th>
                <th>6</th>
                <th>7</th>
                <th>8</th>
            </tr>
        `;
        week.days.forEach(day => {
            const weekRow = document.createElement('tr');
            day.lessons.forEach(lesson => {
                if (lesson === null) {
                    weekRow.innerHTML += `<td class="slot-empty">----</td>`;
                } else {
                    weekRow.innerHTML += `<td class="slot-full">${lesson.subject.name} (${lesson.subject.type})<br>${lesson.group.name}<br>${lesson.room.name}</td>`;
                }
            });
            table.append(weekRow);
        });
        wrapper.append(table);
    });
}

function renderWeather(data, wrapper) {
    // Clear previous content
    wrapper.innerHTML = "";

    data.forEach(weather => {
        const weatherDiv = document.createElement("div");
        weatherDiv.classList.add("weather-slot");
        weatherDiv.innerHTML = `
            <p><strong>Location:</strong> ${weather.location}</p>
            <p><strong>Date:</strong> ${new Date(weather.date).toLocaleString()}</p>
            <p><strong>Temperature:</strong> ${weather.temperature.toFixed(2)}°C</p>
            <p><strong>Wind Direction:</strong> ${weather.windDirection}</p>
            <p><strong>Wind Speed:</strong> ${weather.windSpeed.toFixed(2)} m/s</p>
            <p><strong>Humidity:</strong> ${weather.humidity.toFixed(2)}%</p>
        `;
        wrapper.appendChild(weatherDiv);
    });
}
