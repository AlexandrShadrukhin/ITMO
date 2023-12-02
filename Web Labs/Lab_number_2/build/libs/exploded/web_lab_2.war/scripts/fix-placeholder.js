const inputs = document.querySelectorAll(".input-select");

const placeholderWidths = [...inputs.values()].map(
    (node) => node.getAttribute("placeholder").length
);

const max_width = Math.max(...placeholderWidths);

for (let i = 0; i < inputs.length; i++) {
    inputs[i].setAttribute("size", max_width.toString());
}