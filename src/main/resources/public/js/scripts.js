document.querySelectorAll(".menu").forEach(menu => {
    menu.addEventListener("click", function() {
        let img = menu.querySelector("img");
        if (img && img.hasAttribute("href")) {
            let targetElement = document.getElementById(img.getAttribute("href"));

            if (targetElement) {
                targetElement.scrollIntoView({ behavior: "smooth" });
            }
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const cupcakeBottomRadios = document.querySelectorAll('input[name="cupcakeBottom"]');
    const cupcakeTopRadios = document.querySelectorAll('input[name="cupcakeTop"]');

    function updateTotalPrice() {
        let totalPrice = 0;

        cupcakeBottomRadios.forEach(radio => {
            if (radio.checked) {
                totalPrice += parseFloat(radio.getAttribute("data-salesprice"));
            }
        });

        cupcakeTopRadios.forEach(radio => {
            if (radio.checked) {
                totalPrice += parseFloat(radio.getAttribute("data-salesprice"));
            }
        });

        document.getElementById('totalPrice').textContent = totalPrice.toFixed(2);
    }

    cupcakeBottomRadios.forEach(radio => radio.addEventListener('change', updateTotalPrice));
    cupcakeTopRadios.forEach(radio => radio.addEventListener('change', updateTotalPrice));
});

document.addEventListener("DOMContentLoaded", function () {
    const subtractButton = document.getElementById("cupcake-subtract");
    const addButton = document.getElementById("cupcake-addition");
    const quantityElement = document.getElementById("cupcake-quantity");
    const quantityInput = document.getElementById("cupcakeQuantity");

    subtractButton.addEventListener("click", function () {
        let currentAmount = parseInt(quantityElement.textContent, 10);
        if (currentAmount > 1) {
            quantityElement.textContent = currentAmount - 1;
            quantityInput.value = currentAmount - 1;
        }
    });

    addButton.addEventListener("click", function () {
        let currentAmount = parseInt(quantityElement.textContent, 10);
        quantityElement.textContent = currentAmount + 1;
        quantityInput.value = currentAmount + 1;
    });
});

sessionStorage.setItem("cart", JSON.stringify(cart));
let cart = JSON.parse(sessionStorage.getItem("cart")) || [];

