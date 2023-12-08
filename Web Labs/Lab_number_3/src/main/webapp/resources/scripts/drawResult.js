var resultList = [];

document.querySelectorAll('.result').forEach(function(element) {
    var item = {
        x: parseFloat(element.dataset.x),
        y: parseFloat(element.dataset.y),
        r: parseFloat(element.dataset.r),
        result: element.dataset.result
    };
    resultList.push(item);
    drawShapesAndPoints(item.r);
});

function drawShapesAndPoints(r) {
    drawShapesByR(r, resultList);
}

function getCurrentR() {
    return document.getElementById('choose-form:r-select').value;
}