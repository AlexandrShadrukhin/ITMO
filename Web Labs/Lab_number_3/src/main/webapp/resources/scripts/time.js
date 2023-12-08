const time = document.querySelector("#time");

// обновляем времечко
function updateTime() {
    let date = new Date();
    let separatedDate = date.toLocaleString('ru-RU', { timeZone: 'Europe/Moscow' }).split(",");
    time.textContent = separatedDate[0] + " " + separatedDate[1];
}

updateTime();

setInterval(updateTime, 7000);