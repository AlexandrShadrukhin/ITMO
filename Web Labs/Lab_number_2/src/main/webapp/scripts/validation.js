const VALID_CLASS_ID = "valid";
const INVALID_CLASS_ID = "invalid";
const ERROR_CLASS_ID = "error";
const ERROR_CLASS_ID_ACTIVATE = "error active";
const STRING_EMPTY = "";

const form = document.querySelector("form");
const y_select = document.getElementById("y-select");

const x_error = document.getElementById('x-select-error');
const y_error = y_select.nextElementSibling;
const r_error = document.getElementById('r-select-error');

function containsNumberAfterDecimal(inputString) {
    const regex = /\.0+([1-9])/;

    return regex.test(inputString);
}

const xButtons = document.querySelectorAll('input[name="x-select"]');

xButtons.forEach((button) => {
    button.addEventListener('click', function () {
        xButtons.forEach((btn) => {
            btn.classList.remove('selected');
        });
        this.classList.add('selected');
        const selectedValue = this.value;
        console.log(`Выбранное Y: ${selectedValue}`);
        x_error.textContent = STRING_EMPTY;
        x_error.className = ERROR_CLASS_ID;
    });
});


window.addEventListener("load", () => {
    const isValidY = y_select.value.length === 0 || !Number.isNaN(+y_select.value);
    y_select.className = isValidY ? VALID_CLASS_ID : INVALID_CLASS_ID;
});


y_select.addEventListener("input", () => {
    const y = +y_select.value;


    const isValid = y_select.value.length === 0 || y_select.value === "-"
        || (!Number.isNaN(y) && y >= -5 && y <= 3)
        && !(y_select.value.includes("-5.") && containsNumberAfterDecimal(y_select.value))
        && !(y_select.value.includes("3.") && containsNumberAfterDecimal(y_select.value));
    if (isValid) {
        y_select.className = VALID_CLASS_ID;
        y_error.textContent = STRING_EMPTY;
        y_error.className = ERROR_CLASS_ID;
    } else {
        y_select.className = INVALID_CLASS_ID;
    }
});

const radioButtons = document.querySelectorAll('input[name="r-select"]');

radioButtons.forEach((radioButton) => {
    radioButton.addEventListener('change', function () {
        if (this.checked) {
            const selectedValue = this.value;
            r_error.textContent = STRING_EMPTY;
            r_error.className = ERROR_CLASS_ID;
            drawShapesByR(+selectedValue);
        }
    });
});


form.addEventListener("submit", (event) => {
    let tempX;
    let tempIsSelectedX = false;

    xButtons.forEach((button) => {
        if (button.classList.contains('selected')) {
            tempIsSelectedX = true;
            tempX = button.value;
        }
    });

    const isSelectedX = tempIsSelectedX;
    if (!isSelectedX) {
        xButtons.forEach(async (b) => {
            b.style.backgroundColor = "rgba(255,51,51,0.97)";
            await new Promise(r => setTimeout(r, 300));
            b.style.backgroundColor = "";
        });

        x_error.textContent = "Выберите координату X";
        x_error.className = ERROR_CLASS_ID_ACTIVATE;
    } else {
        x_error.textContent = STRING_EMPTY;
        x_error.className = ERROR_CLASS_ID;
    }

    const x = tempX;

    const y = +y_select.value;
    event.preventDefault();
    const isValidY = y_select.value.length === 0 || !Number.isNaN(y);
    const isAcceptableY = y >= -5 && y <= 3
        && !(y_select.value.includes("-5.") && containsNumberAfterDecimal(y_select.value))
        && !(y_select.value.includes("3.") && containsNumberAfterDecimal(y_select.value));
    if (!isValidY) {
        y_select.className = INVALID_CLASS_ID;
        y_error.textContent = "Введите число";
        y_error.className = ERROR_CLASS_ID_ACTIVATE;
    } else if (!isAcceptableY) {
        y_select.className = INVALID_CLASS_ID;
        y_error.textContent = "Число должно быть в диапазоне от -5 до 3";
        y_error.className = ERROR_CLASS_ID_ACTIVATE;
    } else {
        y_select.className = VALID_CLASS_ID;
        y_error.textContent = STRING_EMPTY;
        y_error.className = ERROR_CLASS_ID;
    }

    let tempR;

    let isSelectedR;

    radioButtons.forEach((radioButton) => {
        if (radioButton.checked) {
            tempR = radioButton.value;
            isSelectedR = true;
        }
    });

    const r = tempR;

    if (isSelectedX && isValidY && isAcceptableY && isSelectedR) {
        send_intersection_rq(x, y, r);
    }
});
