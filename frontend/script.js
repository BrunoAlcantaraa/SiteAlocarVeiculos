const containerCarros = document.getElementById("container-carros");
const containerAlocar = document.getElementById("container-alocar");
const checkboxClienteAlocar = document.getElementById("checkbox-cliente-alocar");

let carros = [
    {
        nome: "Corolla",
        marca: "Toyota",
        placa: "ABC-1234",
        img: "resources/carros/corolla.png",
        cliente: null
    },
    {
        nome: "Civic",
        marca: "Honda",
        placa: "DEF-5678",
        img: "resources/carros/civic.png",
        cliente: null
    },
    {
        nome: "Duster",
        marca: "Renault",
        placa: "GHI-9012",
        img: "resources/carros/duster.png",
        cliente: null
    }
];

let clientes = [
    {
        nome: "Bruno Alcantara"
    }, 
    {
        nome: "Ian Batista"
    }
]

let indexCarroSelecionado = null;

function start() {

    render();

}

function render() {
    containerCarros.innerHTML = "";

    for (let i = 0; i < carros.length; i++) {
        if (carros[i].cliente == null) {
            adicionarCarro(carros[i]);
        }
    }

    containerAlocar.innerHTML = "";

    for (let i = 0; i < clientes.length; i++) {
        let option = document.createElement("option");
        option.value = i;
        option.textContent = clientes[i].nome;
        checkboxClienteAlocar.appendChild(option);
    }
}

function adicionarCarro(carro) {
    let card = document.createElement("div");
    card.classList.add("card-carro");
    card.innerHTML = `
        <img src="${carro.img}" alt="${carro.nome}">
        <h3>${carro.nome}</h3>
        <p>${carro.marca}</p>
        <button onclick="selecionarCarro(${carros.indexOf(carro)})">Selecionar</button>
    `;
    containerCarros.appendChild(card);
}

function selecionarCarro(index) {
    indexCarroSelecionado = index;
    document.getElementById("modal-cliente").style.display = "flex";
}

function alocarCarro() {
    const clienteSelecionado = clientes[checkboxClienteAlocar.value];
    carros[indexCarroSelecionado].cliente = clienteSelecionado;

    alert(`Carro ${carros[indexCarroSelecionado].nome} alocado para ${clienteSelecionado.nome}`);
    fecharModal();
    render();
}

function fecharModal() {
    document.getElementById("modal-cliente").style.display = "none";
}

start();