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
                totalPrice += parseFloat(radio.value);
            }
        });

        cupcakeTopRadios.forEach(radio => {
            if (radio.checked) {
                totalPrice += parseFloat(radio.value);
            }
        });

        document.getElementById('totalPrice').textContent = totalPrice.toFixed(2);
    }

    cupcakeBottomRadios.forEach(radio => radio.addEventListener('change', updateTotalPrice));
    cupcakeTopRadios.forEach(radio => radio.addEventListener('change', updateTotalPrice));
});



sessionStorage.setItem("cart", JSON.stringify(cart));
let cart = JSON.parse(sessionStorage.getItem("cart")) || [];
