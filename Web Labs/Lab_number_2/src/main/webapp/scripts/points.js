let resultList = [];

fetch('Points')
    .then(response => response.json())
    .then(data => {
        data.forEach(point => {
            resultList.push(point);
            drawPoint(point.x, point.y, point.result);
        });
    })
    .catch(error => console.error('Ошибка:', error));
