// al click lista dei partecipanti e il numero di voti ricevuti

let partecipanti = ["Marta", "Paolo", "Francesca", "Simone", "Maurizio", "Cinzia"];

if (document.getElementById("btn-lista")) {
    document.getElementById("btn-lista").addEventListener("click", listaPartecipantiEVoti);
    listaPartecipantiEVoti();
} else {
    listaPartecipanti();
}

function listaPartecipanti() {
    let table = document.getElementById("lista");
    table.innerHTML = "";

    for (let i = 0; i < partecipanti.length; i++) {
        let newRow = table.insertRow();

        let cell = newRow.insertCell();
        cell.textContent = partecipanti[i];
        newRow.appendChild(cell);
    }
}


function listaPartecipantiEVoti() {
    let table = document.getElementById("lista");
    let voti = [];
    table.innerHTML = "";

    let tHead = table.createTHead();

    for (const el of ["#", "Nome", "Voti"]) {
        let newRow = document.createElement("th");
        newRow.setAttribute("scope", "col");
        newRow.textContent = el;
        tHead.appendChild(newRow);
    }

    for (let i = 0; i < partecipanti.length; i++) {
        let newRow = table.insertRow();

        let headerCell = document.createElement("th");
        headerCell.setAttribute("scope", "row");
        headerCell.textContent = i+1; 
        newRow.appendChild(headerCell);

        let cell = newRow.insertCell();
        cell.textContent = partecipanti[i];
        newRow.appendChild(cell);

        cell = newRow.insertCell();
        let randomVote = getRandomInt(10);
        cell.textContent = randomVote;
        voti.push(randomVote);
        newRow.appendChild(cell);
    }

    let tFoot = document.getElementById("table-foot");
    tFoot.innerHTML = "";

    let paragraph = document.createElement("p");
    paragraph.textContent = `Il partecipante con il maggior numero di voti è ${partecipanti[getMaxIndex(voti)]} il numero: ${getMaxIndex(voti) + 1}`;
    tFoot.appendChild(paragraph);

    paragraph = document.createElement("p");
    paragraph.textContent = `Il partecipante con il minor numero di voti è ${partecipanti[getMinIndex(voti)]} il numero: ${getMinIndex(voti) + 1}`;
    tFoot.appendChild(paragraph);

    paragraph = document.createElement("p");
    paragraph.textContent = `La somma dei voti è: ${voti.reduce((sum, val) => sum + val)}`;
    tFoot.appendChild(paragraph);

    shuffle(partecipanti);
}


function getMaxIndex(array) {
    let max = Number.MIN_SAFE_INTEGER;
    for (let x of array) {
        max = array.reduce((max, val) => Math.max(max, val));
    }

    return array.findIndex((el) => el >= max);
}

function getMinIndex(array) {
    let min = Number.MAX_SAFE_INTEGER;
    for (let x of array) {
        min = array.reduce((min, val) => Math.min(min, val));
    }

    return array.findIndex((el) => el <= min);
}

function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}

function shuffle(array) {
    let currentIndex = array.length;

    while (currentIndex != 0) {

        let randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex--;

        [array[currentIndex], array[randomIndex]] = [
            array[randomIndex], array[currentIndex]];
    }
}