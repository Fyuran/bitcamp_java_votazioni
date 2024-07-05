// al click lista dei partecipanti e il numero di voti ricevuti

let partecipanti = [
    {
        nome: "Marta",
        voti: 0
    },
    {
        nome: "Paolo",
        voti: 0
    },
    {
        nome: "Francesca",
        voti: 0
    },
    {
        nome: "Simone",
        voti: 0
    },
    {
        nome: "Maurizio",
        voti: 0
    },
    {
        nome: "Cinzia",
        voti: 0
    }

];
function listaPartecipanti() {

    let elementoListaPartecipanti = document.getElementById("lista");
    elementoListaPartecipanti.innerHTML = "";

    let divRiga = document.createElement("div");
    divRiga.className = "row align-items-center";

    let divColonnaNome = document.createElement("div");
    divColonnaNome.className = "col";
    
    let divColonnaVoti = document.createElement("div");
    divColonnaVoti.className = "col";

    let bNome = document.createElement("b");
    bNome.innerText = "Nome";

    let bVoti = document.createElement("b");
    bVoti.innerText = "Voti";

    divColonnaNome.appendChild(bNome);
    divColonnaVoti.appendChild(bVoti);
    divRiga.appendChild(divColonnaNome);
    divRiga.appendChild(divColonnaVoti);
    elementoListaPartecipanti.appendChild(divRiga);

    for (let i = 0; i < partecipanti.length; i++) {

        let divRigaElem = document.createElement("div");
        divRigaElem.className = "row align-items-center";

        let divColonnaNomeElem = document.createElement("div");
        divColonnaNomeElem.className = "col";
        divColonnaNomeElem.innerText = partecipanti[i].nome;

        let divColonnaVotiElem = document.createElement("div");
        divColonnaVotiElem.className = "col";
        divColonnaVotiElem.innerText = partecipanti[i].voti;
        
        divRigaElem.appendChild(divColonnaNomeElem);
        divRigaElem.appendChild(divColonnaVotiElem);
        elementoListaPartecipanti.appendChild(divRigaElem);
    }

}

/* <div class="row align-items-center">
            <div class="col">
                <b>Nome</b>
            </div>
            <div class="col">
                <b>Voti</b>
            </div>
        </div>



<div class="row align-items-center">
            <div class="col">
                Marta
            </div>
            <div class="col">
                0
            </div>
        </div >*/