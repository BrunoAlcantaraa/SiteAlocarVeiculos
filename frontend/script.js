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
    actionBarActions: document.getElementById("action-bar-actions"),
    tbodyCarros:      document.getElementById("tbody-carros"),
    tabelaCarros:     document.getElementById("tabela-carros"),
    formCarros:       document.getElementById("form-cadastrar-carro"),
    tbodyClientes:      document.getElementById("tbody-clientes"),
    tabelaClientes:     document.getElementById("tabela-clientes"),
    formClientes:       document.getElementById("form-cadastrar-clientes")
};

// ==================== DATA ====================

let veiculos = [
    { nome: "Corolla", renavam: "123456789123", marca: "Toyota",  placa: "ABC-1234", img: "resources/veiculos/corolla.png", cliente: null },
    { nome: "Civic",   renavam: "123456789123", marca: "Honda",   placa: "DEF-5678", img: "resources/veiculos/civic.png",   cliente: null },
    { nome: "Duster",  renavam: "123456789123", marca: "Renault", placa: "GHI-9012", img: "resources/veiculos/duster.png",  cliente: null }
];

let clientes = [
    {
        nome: "Bruno Alcantara",
        cpf: "123.456.789-01",
        email: "bruno@email.com",
        telefone: "(45) 99999-1111"
    },
    {
        nome: "Ian Batista",
        cpf: "234.567.890-12",
        email: "ian@email.com",
        telefone: "(45) 99999-2222"
    }
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
            { label: "Novo Veículo", onclick: "alternarFormularioCarro()", primary: true }
        ]
    },
    "pag-cadastro-clientes": {
        title: "Clientes",
        actions: [
            { label: "Novo Cliente", onclick: "alternarFormularioCliente()", primary: true }
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
    renderTabelaCarros();
    renderTabelaClientes();
}

function renderCardCarros() {
    dom.containerCarros.innerHTML = "";
    const disponiveis = veiculos.filter(c => c.cliente === null);

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
    const alocados = veiculos.filter(c => c.cliente !== null);

    document.getElementById("stat-total-carros").textContent       = veiculos.length;
    document.getElementById("stat-carros-disponiveis").textContent = veiculos.length - alocados.length;
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

function renderTabelaCarros() {
    if (!dom.tbodyCarros) return;

    if (veiculos.length === 0) {
        dom.tbodyCarros.innerHTML = '<tr><td colspan="5" class="empty-state">Nenhum Veículo cadastrado.</td></tr>';
        return;
    }

    dom.tbodyCarros.innerHTML = veiculos.map(veiculo => {
        const statusTexto = veiculo.cliente ? "Alocado" : (veiculo.status || "Disponível");
        const clienteTexto = veiculo.cliente ? veiculo.cliente.nome : "-";

        return `
            <tr>
                <td>${veiculo.nome || "-"}</td>
                <td>${veiculo.marca || "-"}</td>
                <td>${veiculo.placa || "-"}</td>
                <td>${statusTexto}</td>
                <td>${clienteTexto}</td>
            </tr>
        `;
    }).join("");
}

function renderTabelaClientes() {
    if (!dom.tbodyClientes) return;

    if (clientes.length === 0) {
        dom.tbodyClientes.innerHTML = '<tr><td colspan="5" class="empty-state">Nenhum Cliente cadastrado.</td></tr>';
        return;
    }

    dom.tbodyClientes.innerHTML = clientes.map(cliente => {
        return `
            <tr>
                <td>${cliente.nome || "-"}</td>
                <td>${cliente.cpf || "-"}</td>
                <td>${cliente.email || "-"}</td>
                <td>${cliente.telefone || "-"}</td>
            </tr>
        `;
    }).join("");
}

// ==================== NAVEGAÇÃO ====================

function mostrarPagina(paginaId) {
    Object.values(pages).forEach(page => page.style.display = "none");
    const page = document.getElementById(paginaId);
    page.style.display = paginaId === "pag-cadastro-carros" ? "flex" : "block";

    if (paginaId !== "pag-cadastro-carros") {
        fecharFormularioCarro();
    }

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
        <button onclick="selecionarCarro(${veiculos.indexOf(carro)})">Selecionar</button>
    `;
    dom.containerCarros.appendChild(card);
}

function selecionarCarro(index) {
    indexCarroSelecionado = index;
    abrirModalCliente();
}

function alocarCarro() {
    const clienteSelecionado = clientes[dom.selectCliente.value];
    const carroSelecionado   = veiculos[indexCarroSelecionado];

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

    veiculos.push({
        nome: modelo, marca, ano, placa, renavam, cor,
        kms_atual: km, combustivel, valor_diario: valorDiario,
        status, img: imagem, cliente: null
    });

    [
        "input-marca-carro", "input-modelo-carro", "input-ano-carro", "input-placa-carro",
        "input-renavam-carro", "input-cor-carro", "input-km-carro", "input-combustivel-carro",
        "input-valor-diario-carro", "input-status-carro", "input-imagem-carro"
    ].forEach(id => { document.getElementById(id).value = ""; });

    fecharFormularioCarro();
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

function alternarFormularioCarro() {
    if (!dom.formCarros) return;

    if (dom.formCarros.style.display == "block") {
        fecharFormularioCarro();
        dom.tabelaCarros.style.display = "block";
        return;
    }

    dom.tabelaCarros.style.display = "none";
    dom.formCarros.style.display = "block";
}

function fecharFormularioCarro() {
    if (dom.formCarros) {
        dom.formCarros.style.display = "none";
        dom.tabelaCarros.style.display = "block";
    }
}

function alternarFormularioCliente() {
    if (!dom.formClientes) return;

    if (dom.formClientes.style.display == "block") {
        fecharFormularioCliente();
        dom.tabelaClientes.style.display = "block";
        return;
    }

    dom.tabelaClientes.style.display = "none";
    dom.formClientes.style.display = "block";
}

function fecharFormularioCliente() {
    if (dom.formClientes) {
        dom.formClientes.style.display = "none";
        dom.tabelaClientes.style.display = "block";
    }
}

function focarFormCliente() {
    document.getElementById("input-nome-cliente").focus();
}

// ==================== START ====================

init();