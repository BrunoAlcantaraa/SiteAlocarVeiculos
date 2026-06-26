// ==================== DOM ELEMENTS ====================

const pages = {
    dashboard:        document.getElementById("pag-dashboard"),
    alocacao:         document.getElementById("pag-alocacao"),
    cadastroCarros:   document.getElementById("pag-cadastro-carros"),
    cadastroClientes: document.getElementById("pag-cadastro-clientes")
};

const dom = {
    containerCarros:  document.getElementById("container-carros"),
    msgSemCarros:     document.getElementById("msg-sem-carros"),
    modalCliente:     document.getElementById("modal-cliente"),
    selectCliente:    document.getElementById("checkbox-cliente-alocar"),
    actionBarTitle:   document.getElementById("action-bar-title"),
    actionBarActions: document.getElementById("action-bar-actions")
};

// ==================== DATA ====================

let carros = [
    { nome: "Corolla", marca: "Toyota",  placa: "ABC-1234", img: "resources/veiculos/corolla.png", cliente: null },
    { nome: "Civic",   marca: "Honda",   placa: "DEF-5678", img: "resources/veiculos/civic.png",   cliente: null },
    { nome: "Duster",  marca: "Renault", placa: "GHI-9012", img: "resources/veiculos/duster.png",  cliente: null }
];

let clientes = [
    { nome: "Bruno Alcantara" },
    { nome: "Ian Batista" }
];

let indexCarroSelecionado = null;

// ==================== ACTION BAR CONFIG ====================

const actionBarConfig = {
    "pag-dashboard": {
        title: "Dashboard",
        actions: []
    },
    "pag-alocacao": {
        title: "Alocação de Veículos",
        actions: []
    },
    "pag-cadastro-carros": {
        title: "Veículos",
        actions: [
            { label: "Novo Veículo", onclick: "focarFormCarro()", primary: true }
        ]
    },
    "pag-cadastro-clientes": {
        title: "Clientes",
        actions: [
            { label: "Novo Cliente", onclick: "focarFormCliente()", primary: true }
        ]
    }
};

// ==================== INIT ====================

function init() {
    render();
    mostrarPagina("pag-dashboard");
}

// ==================== RENDER ====================

function render() {
    renderCardCarros();
    renderOpcoesClientes();
    renderDashboard();
}

function renderCardCarros() {
    dom.containerCarros.innerHTML = "";
    const disponiveis = carros.filter(c => c.cliente === null);

    if (disponiveis.length === 0) {
        dom.msgSemCarros.style.display = "block";
    } else {
        dom.msgSemCarros.style.display = "none";
        disponiveis.forEach(carro => adicionarCardCarro(carro));
    }
}

function renderOpcoesClientes() {
    dom.selectCliente.innerHTML = "";
    clientes.forEach((cliente, index) => {
        const option = document.createElement("option");
        option.value = index;
        option.textContent = cliente.nome;
        dom.selectCliente.appendChild(option);
    });
}

function renderDashboard() {
    const alocados = carros.filter(c => c.cliente !== null);

    document.getElementById("stat-total-carros").textContent       = carros.length;
    document.getElementById("stat-carros-disponiveis").textContent = carros.length - alocados.length;
    document.getElementById("stat-carros-alocados").textContent    = alocados.length;
    document.getElementById("stat-total-clientes").textContent     = clientes.length;

    const listaEl = document.getElementById("lista-alocacoes");
    if (alocados.length === 0) {
        listaEl.innerHTML = '<p class="empty-state">Nenhum veículo alocado no momento.</p>';
    } else {
        listaEl.innerHTML = alocados.map(c => `
            <div class="alocacao-item">
                <span class="veiculo">${c.nome} — ${c.marca}</span>
                <span class="cliente">${c.cliente.nome}</span>
            </div>
        `).join("");
    }
}

// ==================== NAVEGAÇÃO ====================

function mostrarPagina(paginaId) {
    Object.values(pages).forEach(page => page.style.display = "none");
    document.getElementById(paginaId).style.display = "block";

    document.querySelectorAll(".nav-item").forEach(item => {
        item.classList.toggle("active", item.dataset.page === paginaId);
    });

    const config = actionBarConfig[paginaId];
    dom.actionBarTitle.textContent = config.title;
    dom.actionBarActions.innerHTML = config.actions.map(action =>
        `<button class="btn-action${action.primary ? "" : " outline"}" onclick="${action.onclick}">${action.label}</button>`
    ).join("");
}

// ==================== MODAL ====================

function abrirModalCliente() {
    dom.modalCliente.style.display = "flex";
}

function fecharModal() {
    dom.modalCliente.style.display = "none";
}

// ==================== CARROS ====================

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

function selecionarCarro(index) {
    indexCarroSelecionado = index;
    abrirModalCliente();
}

function alocarCarro() {
    const clienteSelecionado = clientes[dom.selectCliente.value];
    const carroSelecionado   = carros[indexCarroSelecionado];

    carroSelecionado.cliente = clienteSelecionado;
    alert(`${carroSelecionado.nome} alocado para ${clienteSelecionado.nome}.`);

    fecharModal();
    render();
}

// ==================== CADASTRO ====================

function cadastrarCarro() {
    const marca       = document.getElementById("input-marca-carro").value.trim();
    const modelo      = document.getElementById("input-modelo-carro").value.trim();
    const ano         = document.getElementById("input-ano-carro").value.trim();
    const placa       = document.getElementById("input-placa-carro").value.trim();
    const renavam     = document.getElementById("input-renavam-carro").value.trim();
    const cor         = document.getElementById("input-cor-carro").value.trim();
    const km          = document.getElementById("input-km-carro").value.trim();
    const combustivel = document.getElementById("input-combustivel-carro").value;
    const valorDiario = document.getElementById("input-valor-diario-carro").value.trim();
    const status      = document.getElementById("input-status-carro").value;
    const imagem      = document.getElementById("input-imagem-carro").value.trim();

    if (!marca || !modelo || !placa || !renavam) return;

    carros.push({
        nome: modelo, marca, ano, placa, renavam, cor,
        kms_atual: km, combustivel, valor_diario: valorDiario,
        status, img: imagem, cliente: null
    });

    [
        "input-marca-carro", "input-modelo-carro", "input-ano-carro", "input-placa-carro",
        "input-renavam-carro", "input-cor-carro", "input-km-carro", "input-combustivel-carro",
        "input-valor-diario-carro", "input-status-carro", "input-imagem-carro"
    ].forEach(id => { document.getElementById(id).value = ""; });

    render();
}

function cadastrarCliente() {
    const nome        = document.getElementById("input-nome-cliente").value.trim();
    const cpf         = document.getElementById("input-cpf-cliente").value.trim();
    const nascimento  = document.getElementById("input-nascimento-cliente").value;
    const sexo        = document.getElementById("input-sexo-cliente").value;
    const email       = document.getElementById("input-email-cliente").value.trim();
    const telefone    = document.getElementById("input-telefone-cliente").value.trim();
    const cep         = document.getElementById("input-cep-cliente").value.trim();
    const estado      = document.getElementById("input-estado-cliente").value.trim();
    const cidade      = document.getElementById("input-cidade-cliente").value.trim();
    const bairro      = document.getElementById("input-bairro-cliente").value.trim();
    const rua         = document.getElementById("input-rua-cliente").value.trim();
    const numEnd      = document.getElementById("input-numero-end-cliente").value.trim();
    const complemento = document.getElementById("input-complemento-cliente").value.trim();

    if (!nome || !cpf) return;

    clientes.push({
        nome, cpf, nascimento, sexo, email, telefone,
        endereco: { cep, estado, cidade, bairro, rua, numero: numEnd, complemento }
    });

    [
        "input-nome-cliente", "input-cpf-cliente", "input-nascimento-cliente", "input-sexo-cliente",
        "input-email-cliente", "input-telefone-cliente", "input-cep-cliente", "input-estado-cliente",
        "input-cidade-cliente", "input-bairro-cliente", "input-rua-cliente",
        "input-numero-end-cliente", "input-complemento-cliente"
    ].forEach(id => { document.getElementById(id).value = ""; });

    renderOpcoesClientes();
    renderDashboard();
}

// ==================== FORM FOCUS ====================

function focarFormCarro() {
    document.getElementById("input-marca-carro").focus();
}

function focarFormCliente() {
    document.getElementById("input-nome-cliente").focus();
}

// ==================== START ====================

init();