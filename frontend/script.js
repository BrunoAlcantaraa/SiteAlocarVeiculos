// ==================== DOM ELEMENTS ====================

// Páginas
const pages = {
    carros: document.getElementById("pag-carros-disponiveis"),
    cadastroCarros: document.getElementById("pag-cadastro-carros"),
    cadastroClientes: document.getElementById("pag-cadastro-clientes")
};

// Container - Carros Disponíveis
const dom = {
    containerCarros: document.getElementById("container-carros"),
    containerAlocar: document.getElementById("container-alocar"),
    modalCliente: document.getElementById("modal-cliente"),
    selectCliente: document.getElementById("checkbox-cliente-alocar")
};

// ==================== DATA ====================
let carros = [
    {        
        nome: "Corolla",
        marca: "Toyota",
        placa: "ABC-1234",
        img: "resources/veiculos/corolla.png",
        cliente: null
    },
    {
        nome: "Civic",
        marca: "Honda",
        placa: "DEF-5678",
        img: "resources/veiculos/civic.png",
        cliente: null
    },
    {
        nome: "Duster",
        marca: "Renault",
        placa: "GHI-9012",
        img: "resources/veiculos/duster.png",
        cliente: null
    }
];

let clientes = [
    { nome: "Bruno Alcantara" },
    { nome: "Ian Batista" }
];

let indexCarroSelecionado = null;

// ==================== INIT ====================

function init() {
    render();
}

// ==================== RENDER ====================

// Renderiza todos os carros disponíveis
function render() {
    renderCardCarros();
    renderOpcoesClientes();
}

// Renderiza cards dos carros sem cliente
function renderCardCarros() {
    dom.containerCarros.innerHTML = "";
    
    carros
        .filter(carro => carro.cliente === null)
        .forEach(carro => adicionarCardCarro(carro));
}

// Renderiza lista de clientes no select
function renderOpcoesClientes() {
    dom.selectCliente.innerHTML = "";
    
    clientes.forEach((cliente, index) => {
        const option = document.createElement("option");
        option.value = index;
        option.textContent = cliente.nome;
        dom.selectCliente.appendChild(option);
    });
}

// ==================== NAVEGAÇÃO ====================

// Mostra a página solicitada e esconde as outras
function mostrarPagina(paginaId) {
    Object.values(pages).forEach(page => page.style.display = "none");
    document.getElementById(paginaId).style.display = "block";
}

// ==================== MODAL ====================

// Abre modal de seleção de cliente
function abrirModalCliente() {
    dom.modalCliente.style.display = "flex";
}

// Fecha modal
function fecharModal() {
    dom.modalCliente.style.display = "none";
}

// ==================== CARROS ====================

// Cria e adiciona card de carro ao DOM
function adicionarCardCarro(carro) {
    const card = document.createElement("div");
    card.classList.add("card-carro");
    card.innerHTML = `
        <img src="${carro.img}" alt="${carro.nome}">
        <h3>${carro.nome}</h3>
        <p>${carro.marca}</p>
        <button onclick="selecionarCarro(${carros.indexOf(carro)})">Selecionar</button>
    `;
    dom.containerCarros.appendChild(card);
}

// Seleciona um carro e abre modal
function selecionarCarro(index) {
    indexCarroSelecionado = index;
    abrirModalCliente();
}

// Aloca carro para cliente
function alocarCarro() {
    const clienteSelecionado = clientes[dom.selectCliente.value];
    const carroSelecionado = carros[indexCarroSelecionado];
    
    carroSelecionado.cliente = clienteSelecionado;
    alert(`Carro ${carroSelecionado.nome} alocado para ${clienteSelecionado.nome}`);
    
    fecharModal();
    render();
}

// ==================== START ====================

init();