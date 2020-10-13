const request = axios.create({
    headers: {
        'Authorization':'Bearer ' + localStorage.getItem("token")
    }
});

$(document).ready(async function () {
    await getChats();
    await getPhoto();
});

function hideChats() {
    $('#chats').css('display', 'none');
    changeTitle();
    showBackButton();
}


function changeTitle() {
    document.getElementById('title').innerText='Поиск';
}

function showBackButton() {
    $('.title button').css('display','block');
}



function redirectProfile() {
    document.location.href="/upload";
}

async function getPhoto() {
    const response = await request.get('/photo');
    console.log(response);
    if(response.data!=="")
       document.getElementById('photo').src="data:image/png;base64," + response;
}


function drawChats(messageText = "") {
    const message = document.createElement('button');
    message.innerText = messageText;
    const chats = document.getElementById('chats');
    chats.appendChild(message);
}


async function getChats() {
    try {
        const response = await request.get('/c/allChats');
        $('body').css('display','block');
        if (response.data.length === 0) {
            drawChats("No chats");
        } else {
            for(let i=0;i<=response.data.length-1;i++) {
                drawChats(response.data[i]);
            }
        }
    } catch (err) {
        //if (err.response.status === 401)
          //  document.location.href = "/login";
    }
}




function drawMessage(messageText = "") {
    const message = document.createElement('p');
    message.innerText = messageText;
    message.id = "message";
    document.getElementById('users').appendChild(message);
}



function getUsers() {
    let timeout;
    if (timeout) {
        clearTimeout(timeout);
    }

    timeout = setTimeout(async function () {

        // отчищаем блок с юзерами , чтобы поместить туда новых юзеров
        clearUsers();

        // получаем логин с поля и создаём объект
        const login = document.getElementById('search').value;
        const data = await getUsersRequest(login);

        console.log(data.length);
        if (data.length === 1) {
            drawMessage("Not found");
        } else {
            await drawUsers(data)
        }

    });

}

async function getUsersRequest(login) {
    try {
        const response = await request.post('/u/users', {login});
        return response.data;
    } catch(err) {
        if (err.response.status === 401) {
            document.location.href = '/login';
        }
    }
}

async function drawUsers(data) {
    const users = document.getElementById('users');
    for (let i = 0; i <= data.length - 1; i++) {
        const login = await getCurrentUser();
        if(login.login!==data[i].login) {
            const user = document.createElement('button');
            user.innerText = data[i].login;
            user.onclick = async function () {

                try {
                    const response = await request.post('/c/chat', {
                        list: [
                            {login:login.login},
                            {login:data[i].login}
                        ]
                    });
                } catch (err) {
                    if (err.response.status === 401) {
                        document.location.href = '/login';
                    }
                }
            };
            users.appendChild(user);
        }
    }
}


function clearUsers() {
    const users = document.getElementById('users');
    const user = users.getElementsByTagName('button');
    for (let i = 0; i <= user.length - 1; i++) {
        users.removeChild(user[i]);
    }
    const message = document.getElementById("message");
    if (message != null)
        users.removeChild(message);
}






async function getCurrentUser() {
    try {
        const response = await request.get('/u/auth/user');
        return response.data;
    } catch (err) {
        if (err.status === 401) {
            document.location.href = "/login";
        }
    }
}



