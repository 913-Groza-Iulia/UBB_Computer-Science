document.addEventListener("DOMContentLoaded", function () {
  const menuOptions = document.querySelectorAll(".menu-option"); //selects all elements with the menu-option class
  const dropdowns = document.querySelectorAll(".dropdown"); //selects all elements with the dropdown class

  menuOptions.forEach((option, index) => {
    //iterates through each menu option
    option.addEventListener("click", function () {
      dropdowns.forEach((dropdown) => {
        //initially hide all the dropdowns submenus
        dropdown.style.display = "none";
      });

      // Show the corresponding dropdown
      const dropdownToShow = document.getElementById(`dropdown${index + 1}`);
      if (dropdownToShow) {
        dropdownToShow.style.display = "flex";
      }
    });
  });

  // Hide dropdowns when clicking outside of them
  document.addEventListener("click", function (event) {
    if (
      !event.target.matches(".menu-option") &&
      !event.target.matches(".dropdown-item")
    ) {
      dropdowns.forEach((dropdown) => {
        dropdown.style.display = "none";
      });
    }
  });
});
