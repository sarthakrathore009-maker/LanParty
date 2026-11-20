// 1. Array banaya players ko store karne ke liye (Jaise Java me ArrayList banayi thi)
let players = [];

// 2. HTML elements ko JS me pakda (id ke through)
const nameInput = document.getElementById("playerName");
const levelInput = document.getElementById("playerLevel");
const joinBtn = document.getElementById("joinBtn");
const playersList = document.getElementById("playersList");
const playerCount = document.getElementById("playerCount");
const emptyMsg = document.getElementById("emptyMsg");

// 3. Jab koi "Join" button click karega tab ye function chalega
joinBtn.addEventListener("click", function() {
    
    // Inputs ki value nikali
    const nameValue = nameInput.value.trim();
    const levelValue = levelInput.value.trim();

    // Agar dono me se koi ek bhi box khali hai, toh ruk jao
    if (nameValue === "" || levelValue === "") {
        alert("Please enter both Name and Level!");
        return;
    }

    // Naya player object banaya
    const newPlayer = {
        id: Math.floor(Math.random() * 1000), // Random ID generate ki
        name: nameValue,
        level: parseInt(levelValue) // Level ko number me convert kiya
    };

    // 🔴 LATER: BACKEND CONNECTION 🔴
    // Jab Java Server ready hoga, hum yahan se data bhejenge:
    // fetch('http://localhost:5000/api/join', { method: 'POST', body: JSON.stringify(newPlayer) })

    // Array me player ko add kiya (Java ke LP.add(P) jaisa)
    players.push(newPlayer);

    // Inputs ko wapas khali kar diya
    nameInput.value = "";
    levelInput.value = "";

    // Screen (UI) update karo
    updateUI();
});


// 4. Ye function Array ke data ko HTML screen par print karta hai
function updateUI() {
    // Total count update kiya
    playerCount.innerText = players.length;

    // Agar array me log hain, toh "Lobby is empty" wala message hata do
    if (players.length > 0) {
        emptyMsg.style.display = "none";
    }

    // Pehle list ko pura clear karo, warna purane wale double print ho jayenge
    playersList.innerHTML = "";

    // Array me loop lagaya (Java ke "for (Player p : LP.values())" jaisa)
    for (let i = 0; i < players.length; i++) {
        let p = players[i];
        
        // Ek naya HTML list item (<li>) banaya
        let li = document.createElement("li");
        
        // Usme player ka data daala
        li.innerHTML = `
            <strong>${p.name}</strong> 
            <span class="player-info">Level: ${p.level} | ID: ${p.id}</span>
        `;
        
        // List ko HTML wali main ul me add kar diya
        playersList.appendChild(li);
    }
}