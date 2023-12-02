function send_intersection_rq(x, y, r) {
    const hidden_form = document.querySelector("#hidden-form")
    const hidden_x = document.querySelector("#hidden-x");
    hidden_x.value = x;
    const hidden_y = document.querySelector("#hidden-y");
    hidden_y.value = y;
    const hidden_r = document.querySelector("#hidden-r");
    hidden_r.value = r;
    hidden_form.submit();
}
