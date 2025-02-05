const apiWhiteElephantUrl = '/games/white-elephant';
let gamesData = []; 

function selectGames() {
    fetch(apiWhiteElephantUrl+"/all")
  .then(response => response.json())
  .then(data => {
    const dropdownMenu = document.getElementById("dropdownMenu");
    dropdownMenu.innerHTML= "";
    gamesData = data;
    data.forEach(game => {
        let gameItem = document.createElement("a");
        gameItem.textContent = game.name;
        gameItem.onclick = () => selectGame(game.name); // Action on click
        dropdownMenu.appendChild(gameItem);
      console.log(`${game.name} - Players: ${game.players}, list: ${game.list}`);
    });
    toggleDropdown()
  })
  .catch(error => console.error("Error fetching games:", error));
}

 // Function to toggle dropdown visibility
 function toggleDropdown() {
    document.querySelector(".dropdown").classList.toggle("show");
}

// Close dropdown if clicking outside
window.onclick = function(event) {
    if (!event.target.matches(".dropdown-btn")) {
        document.querySelector(".dropdown").classList.remove("show");
    }
}

function selectGame(gameName) {
    document.getElementById("selectedGame").value = gameName;
    document.getElementById("gameForm").submit(); // Submit form to Thymeleaf backend
}
