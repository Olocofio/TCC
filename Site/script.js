// JavaScript para destacar o menu ativo com base na rolagem
window.addEventListener('scroll', function () {
    let sections = document.querySelectorAll('section');
    let navLinks = document.querySelectorAll('#navbar ul li a');

    sections.forEach(section => {
        let top = window.scrollY;
        let offset = section.offsetTop - 150;
        let height = section.offsetHeight;
        let id = section.getAttribute('id');

        if (top >= offset && top < offset + height) {
            navLinks.forEach(link => {
                link.classList.remove('active');
                document.querySelector('#navbar ul li a[href*=' + id + ']').classList.add('active');
            });
        }
    });
});
