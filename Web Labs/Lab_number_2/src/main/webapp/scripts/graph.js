const canvas = document.getElementById("graph");
const context = canvas.getContext("2d");
const xAxisLabel = "X";
const yAxisLabel = "Y";
let xAxisScale;
let yAxisScale;

function draw() {
    if (canvas.getContext) {
        context.fillStyle = "rgb(0,0,0)";

        let canvasWidth = canvas.width;
        let canvasHeight = canvas.height;

        xAxisScale = canvasWidth / 10;
        yAxisScale = canvasHeight / 10;

        let originX = canvasWidth / 2;
        let originY = canvasHeight / 2;

        context.beginPath();
        context.moveTo(0, originY);
        context.lineTo(canvasWidth, originY);
        context.stroke();

        context.beginPath();
        context.moveTo(originX, 0);
        context.lineTo(originX, canvasHeight);
        context.stroke();

        context.font = '14px Arial';
        context.fillText(xAxisLabel, canvas.width - 15, canvas.height / 2 - 5);
        context.fillText(yAxisLabel, canvas.width / 2 + 5, 15);

        for (let i = -canvas.width / 2; i < canvas.width / 2; i += xAxisScale) {
            let scalePos = axesToCanvasCoordinates(i, 0, canvas);
            context.beginPath();
            context.moveTo(scalePos.x, scalePos.y - 5);
            context.lineTo(scalePos.x, scalePos.y + 5);
            context.stroke();
            context.fillText(rescaleXAxesCoordinate(i), scalePos.x - 10, scalePos.y + 20);
        }

        for (let j = -canvas.height / 2; j < canvas.height / 2; j += yAxisScale) {
            let scalePos = axesToCanvasCoordinates(0, j, canvas);
            context.beginPath();
            context.moveTo(scalePos.x - 5, scalePos.y);
            context.lineTo(scalePos.x + 5, scalePos.y);
            context.stroke();
            context.fillText(rescaleYAxesCoordinate(j), scalePos.x + 10, scalePos.y + 5);
        }

    }
}

function axesToCanvasCoordinates(xAxes, yAxes, canvas) {

    let originX = canvas.width / 2;
    let originY = canvas.height / 2;


    let canvasX = originX + xAxes;
    let canvasY = originY - yAxes;

    return {x: canvasX, y: canvasY};
}

function rescaleXAxesCoordinate(coordinate) {
    return coordinate / xAxisScale;
}

function rescaleYAxesCoordinate(coordinate) {
    return coordinate / yAxisScale;
}

function scaleXAxesCoordinate(coordinate) {
    return coordinate * xAxisScale;
}

function scaleYAxesCoordinate(coordinate) {
    return coordinate * yAxisScale;
}

function drawShapesByR(r) {
    if (canvas.getContext) {

        context.clearRect(0, 0, canvas.width, canvas.height);
        draw();


        let startPointInAxes = {x: 0, y: 0};
        let startPointInCanvas = axesToCanvasCoordinates(startPointInAxes.x, startPointInAxes.y, canvas);


        let endPointInAxes = {x: -r, y: -r / 2};
        let endScaledPointInAxes = {
            x: scaleXAxesCoordinate(endPointInAxes.x),
            y: scaleYAxesCoordinate(endPointInAxes.y)
        };


        context.fillStyle = "rgba(0,255,21,0.75)";

        context.beginPath();
        context.fillRect(startPointInCanvas.x, startPointInCanvas.y, endScaledPointInAxes.x, -endScaledPointInAxes.y);

        let secondTrianglePointInAxes = {x: r, y: 0};
        let thirdTrianglePointInAxes = {x: 0, y: -r / 2};
        context.fillStyle = "rgba(0,255,21,0.75)";
        drawTriangle(context, startPointInAxes, secondTrianglePointInAxes, thirdTrianglePointInAxes);
        context.fillStyle = "rgba(0,255,21,0.75)";

        let calculatedRadius = scaleXAxesCoordinate(r);
        let startAngle = 0;
        let endAngle = 3 * Math.PI / 2;

        context.beginPath();
        context.arc(startPointInCanvas.x, startPointInCanvas.y, calculatedRadius, startAngle, endAngle, true);
        context.fill();

        let secondTrianglePointInAxesM = {x: r, y: 0};
        let thirdTrianglePointInAxesM = {x: 0, y: r};
        drawTriangle(context, startPointInAxes, secondTrianglePointInAxesM, thirdTrianglePointInAxesM);
        for (let i = 0; i < resultList.length; i++) {
            const point = resultList[i];
            drawPoint(point.x, point.y, point.result);
        }
    }
}

function drawTriangle(ctx, startPointInAxes, secondTrianglePointInAxes, thirdTrianglePointInAxes) {
    if (canvas.getContext) {
        let startPointInCanvas = axesToCanvasCoordinates(startPointInAxes.x, startPointInAxes.y, canvas);
        let secondScaledTrianglePointInAxes = {
            x: scaleXAxesCoordinate(secondTrianglePointInAxes.x),
            y: scaleYAxesCoordinate(secondTrianglePointInAxes.y)
        }
        let thirdScaledTrianglePointInAxes = {
            x: scaleXAxesCoordinate(thirdTrianglePointInAxes.x),
            y: scaleYAxesCoordinate(thirdTrianglePointInAxes.y)
        };
        let secondTrianglePointInCanvas = axesToCanvasCoordinates
        (secondScaledTrianglePointInAxes.x, secondScaledTrianglePointInAxes.y, canvas);
        let thirdScaledTrianglePointInCanvas = axesToCanvasCoordinates
        (thirdScaledTrianglePointInAxes.x, thirdScaledTrianglePointInAxes.y, canvas);

        ctx.beginPath();
        ctx.moveTo(startPointInCanvas.x, startPointInCanvas.y);
        ctx.lineTo(secondTrianglePointInCanvas.x, secondTrianglePointInCanvas.y);
        ctx.lineTo(thirdScaledTrianglePointInCanvas.x, thirdScaledTrianglePointInCanvas.y);
        ctx.fill();
    }
}

function drawPoint(x, y, result) {
    if (canvas.getContext) {
        const pointSize = 4;
        let scaledPoint = {x: scaleXAxesCoordinate(x), y: scaleYAxesCoordinate(y)};
        let pointOnCanvas = axesToCanvasCoordinates(scaledPoint.x, scaledPoint.y, canvas);
        result ? context.fillStyle = "rgb(200,0,0)" : context.fillStyle = "rgb(0,0,0)";
        context.beginPath();
        context.fillRect(pointOnCanvas.x - pointSize / 2, pointOnCanvas.y - pointSize / 2, pointSize, pointSize);
    }
}

canvas.addEventListener("click", handleCanvasClick);

function handleCanvasClick(event) {
    const rect = canvas.getBoundingClientRect();
    const xCanvas = event.clientX - rect.left;
    const yCanvas = event.clientY - rect.top;

    const xAxes = (xCanvas - canvas.width / 2) / xAxisScale;
    const yAxes = -(yCanvas - canvas.height / 2) / yAxisScale;

    console.log(`Кликнуто на точку (${xAxes}, ${yAxes})`);
    let tempIsSelected = false;
    let tempR;

    radioButtons.forEach(radioButton => {
        if (radioButton.checked) {
            tempR = radioButton.value;
            tempIsSelected = true;
            const r = tempR;
            send_intersection_rq(xAxes, yAxes, r);
            draw();
        }
    });


    const isSelectedR = tempIsSelected;

    if (!isSelectedR) {
        r_error.textContent = "Выберите значение R";
        r_error.className = ERROR_CLASS_ID_ACTIVATE;
        return;
    }

    const r = tempR;
    send_intersection_rq(xAxes, yAxes, r);
    draw();
}

draw();
