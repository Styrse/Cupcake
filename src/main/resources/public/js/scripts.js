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
