
function changeVisibility() {
    const visible = "http://localhost:8080/static/img/visible.png";
    const notVisible = "http://localhost:8080/static/img/not-visible.png";
    const image = document.getElementById("eye-visibility");
    const input = document.getElementById("password");
    console.log(image.src);
    if(image.src===visible) {
        image.src=image.src.replace(visible,notVisible);
        input.type="";
    }
    else if(image.src===notVisible) {
        image.src=image.src.replace(notVisible,visible);
        input.type="password";
    }
}

function checkCreated() {
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;
    const error = $('#error');

    if(login==='' || password==='' || login.includes(' ') || password.includes(' ')) {
        document.getElementById('error').innerText="Недопустимый логин или пароль";
        showErrorOne(error);
    }

    else {
        axios.post('/checkCreated', {
            login: login
        })
            .then(function (response) {
                if (response.data === false)
                    registration(login, password);
                else {
                    document.getElementById('error').innerText="Данный логин уже используется";
                    showErrorTwo(error);
                }
            })
            .catch(function (err) {
                console.log(err);
            });
    }

}



function registration(login,password) {

      axios.post('/registration',{
          login:login,
          password:password
      })
          .then(function (response) {
              localStorage.setItem("token",response.data.token);
              document.location.href="/chats";
          })
          .catch(function (err) {
             console.log(err);
          });

}