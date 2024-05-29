document
    .getElementById("room-timetable-form")
    .addEventListener("submit", async (event) => {
        event.preventDefault()
        const formData = new FormData(event.target)
        const response = await fetch("https://vm.nathoro.ru/timetable/by-room/" + encodeURIComponent(formData.get("room")))
        const data = await response.json()

        // or
        // fetch("https://vm.nathoro.ru/timetable/by-room/" + encodeURIComponent(formData.get("room")))
        // .then(r => r.json())
        // .then(d => console.log(d))

        console.log(data)

        const wrapper = document.getElementById("timetable-wrapper")
        renderWeek(data[0], wrapper) // 1 неделя
        renderWeek(data[1], wrapper) // 2 неделя
    })

    let renderWeek = (data, wrapper) => {
        // wrapper.append(JSON.stringify(data))

        const table = document.createElement("table")
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
        `
        data.days.forEach(day => {
            const week = document.createElement('tr')
            day.lessons.forEach(lesson => {
                if (lesson === null) {
                    week.innerHTML += `<td class="slot-empty">----</td>`
                } else {
                    week.innerHTML += `<td class="slot-full">${lesson.group.name}</td>`
                }
            })
            table.append(week)
        })
        wrapper.append(table)
    }