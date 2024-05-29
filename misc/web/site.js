document.getElementById("signInForm")
    .addEventListener("submit", (event) => {
        event.preventDefault();
        const currentForm = event.target;
        const formData = new FormData(currentForm);
        const login = formData.get("login");

        fetch("groups.json") // https://time.ulstu.ru/api/1.0/groups
            //.then((data) => { return data.json(); })
            .then(data => data.json())
            .then(data => {
                //console.log(data);

                // Поиск контейнера в DOM-дереве.
                const root = document.getElementById("root");
                root.innerHTML = '';

                // Создание нового элемента.
                const list = document.createElement("ul");

                for (let index = 0; index < data.response.length; index++) {
                    const groupName = data.response[index];
                    const groupListItem = document.createElement("li");
                    groupListItem.innerHTML = groupName;
                    if (groupName.includes(login)) { // Если в логине будет "ПИ", то красотааа
                        groupListItem.classList.add("cool");
                    }
                    list.appendChild(groupListItem);
                }

                // Добавление элемента к другому.
                root.appendChild(list);
            });
    });

document.getElementById("clearBtn")
    .addEventListener("click", () => {
        const root = document.getElementById("root");
        root.innerHTML = '';
    });
