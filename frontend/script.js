/*
    DOM
*/

const pages = {
    "pag-dashboard":         document.getElementById("pag-dashboard"),
    "pag-alocacao":          document.getElementById("pag-alocacao"),
    "pag-cadastro-veiculos": document.getElementById("pag-cadastro-veiculos"),
    "pag-cadastro-clientes": document.getElementById("pag-cadastro-clientes")
};

const dom = {
    loginScreen:         document.getElementById("login-screen"),
    layout:              document.getElementById("layout"),
    containerVeiculos:   document.getElementById("container-veiculos"),
    msgSemVeiculos:      document.getElementById("msg-sem-veiculos"),
    modalCliente:        document.getElementById("modal-cliente"),
    selectCliente:       document.getElementById("checkbox-cliente-alocar"),
    actionBarTitle:      document.getElementById("action-bar-title"),
    actionBarActions:    document.getElementById("action-bar-actions"),
    tbodyVeiculos:       document.getElementById("tbody-veiculos"),
    tabelaVeiculos:      document.getElementById("tabela-veiculos"),
    formVeiculos:        document.getElementById("form-cadastrar-veiculo"),
    tbodyClientes:       document.getElementById("tbody-clientes"),
    tabelaClientes:      document.getElementById("tabela-clientes"),
    formClientes:        document.getElementById("form-cadastrar-clientes"),
    statTotalVeiculos:   document.getElementById("stat-total-veiculos"),
    statDisponiveis:     document.getElementById("stat-veiculos-disponiveis"),
    statAlocados:        document.getElementById("stat-veiculos-alocados"),
    statTotalClientes:   document.getElementById("stat-total-clientes"),
    listaAlocacoes:      document.getElementById("lista-alocacoes"),
};

/*
    DADOS
*/

let veiculos = [
    { nome: "Corolla", renavam: "123456789123", marca: "Toyota",  placa: "ABC-1234", img: "resources/veiculos/corolla.png", status: "Disponível", cliente: null },
    { nome: "Civic",   renavam: "123456789123", marca: "Honda",   placa: "DEF-5678", img: "resources/veiculos/civic.png",   status: "Disponível", cliente: null },
    { nome: "Duster",  renavam: "123456789123", marca: "Renault", placa: "GHI-9012", img: "resources/veiculos/duster.png",  status: "Disponível", cliente: null }
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

let indexVeiculoSelecionado = null;

/*
    CONFIG. ACTION BAR
*/

const actionBarConfig = {
    "pag-dashboard": {
        title: "Dashboard",
        actions: []
    },
    "pag-alocacao": {
        title: "Alocação de Veículos",
        actions: []
    },
    "pag-cadastro-veiculos": {
        title: "Veículos",
        actions: [
            { label: "Novo Veículo", onclick: "alternarFormularioVeiculo()", primary: true }
        ]
    },
    "pag-cadastro-clientes": {
        title: "Clientes",
        actions: [
            { label: "Novo Cliente", onclick: "alternarFormularioCliente()", primary: true }
        ]
    }
};

/*
    LOGIN
*/

function fazerLogin(event) {
    event.preventDefault();
    const usuario = document.getElementById("input-login-usuario").value.trim();
    const senha   = document.getElementById("input-login-senha").value.trim();
    const erro    = document.getElementById("login-erro");

    if (!usuario || !senha) {
        erro.textContent = "Preencha todos os campos.";
        return;
    }

    // Futuramente: substituir por chamada à API de autenticação
    // const autenticado = await api.autenticar({ usuario, senha });
    // if (!autenticado) { erro.textContent = "Usuário ou senha inválidos."; return; }

    erro.textContent = "";
    dom.loginScreen.style.display = "none";
    dom.layout.style.display      = "flex";
    init();
}

/*
    INICIO
*/
function init() {
    render();
    mostrarPagina("pag-dashboard");
}

/*
    RENDER
*/

function render() {
    limparBuscas();
    renderCardsVeiculos();
    renderOpcoesClientes();
    renderDashboard();
    renderTabelaVeiculos();
    renderTabelaClientes();
}

function limparBuscas() {
    ["busca-alocacao", "busca-veiculos", "busca-clientes"].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.value = "";
    });
}

function renderCardsVeiculos(dados) {
    const lista = dados !== undefined ? dados : veiculos.filter(v => v.cliente === null);
    dom.containerVeiculos.innerHTML = "";
    dom.msgSemVeiculos.style.display = lista.length === 0 ? "block" : "none";
    lista.forEach(v => adicionarCardVeiculo(v));
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
    const alocados = veiculos.filter(v => v.cliente !== null);

    dom.statTotalVeiculos.textContent = veiculos.length;
    dom.statDisponiveis.textContent   = veiculos.length - alocados.length;
    dom.statAlocados.textContent      = alocados.length;
    dom.statTotalClientes.textContent = clientes.length;

    dom.listaAlocacoes.innerHTML = alocados.length === 0
        ? '<p class="empty-state">Nenhum veículo alocado no momento.</p>'
        : alocados.map(v => `
            <div class="alocacao-item">
                <span class="veiculo">${v.nome} — ${v.marca}</span>
                <span class="cliente">${v.cliente.nome}</span>
            </div>
        `).join("");
}

function renderTabelaVeiculos(dados) {
    if (!dom.tbodyVeiculos) return;
    const lista = dados !== undefined ? dados : veiculos;

    if (lista.length === 0) {
        dom.tbodyVeiculos.innerHTML = '<tr><td colspan="5" class="empty-state">Nenhum veículo encontrado.</td></tr>';
        return;
    }

    dom.tbodyVeiculos.innerHTML = lista.map(v => {
        const statusTexto  = v.cliente ? "Alocado" : (v.status || "Disponível");
        const clienteTexto = v.cliente ? v.cliente.nome : "-";
        return `
            <tr>
                <td>${v.nome  || "-"}</td>
                <td>${v.marca || "-"}</td>
                <td>${v.placa || "-"}</td>
                <td>${statusTexto}</td>
                <td>${clienteTexto}</td>
            </tr>
        `;
    }).join("");
}

function renderTabelaClientes(dados) {
    if (!dom.tbodyClientes) return;
    const lista = dados !== undefined ? dados : clientes;

    if (lista.length === 0) {
        dom.tbodyClientes.innerHTML = '<tr><td colspan="5" class="empty-state">Nenhum cliente encontrado.</td></tr>';
        return;
    }

    dom.tbodyClientes.innerHTML = lista.map(c => `
        <tr>
            <td>${c.nome     || "-"}</td>
            <td>${c.cpf      || "-"}</td>
            <td>${c.email    || "-"}</td>
            <td>${c.telefone || "-"}</td>
        </tr>
    `).join("");
}

/*
    BUSCA / FILTRO
    Para integrar com API: substituir o corpo das funções buscar*
    por uma chamada fetch() e passar o resultado para o render correspondente.
*/

function buscarVeiculos(termo) {
    if (!termo) return veiculos;
    const t = termo.toLowerCase();
    return veiculos.filter(v =>
        (v.nome   || "").toLowerCase().includes(t) ||
        (v.marca  || "").toLowerCase().includes(t) ||
        (v.placa  || "").toLowerCase().includes(t) ||
        (v.status || "").toLowerCase().includes(t)
    );
}

function buscarClientes(termo) {
    if (!termo) return clientes;
    const t = termo.toLowerCase();
    return clientes.filter(c =>
        (c.nome  || "").toLowerCase().includes(t) ||
        (c.cpf   || "").toLowerCase().includes(t) ||
        (c.email || "").toLowerCase().includes(t)
    );
}

function buscarVeiculosDisponiveis(termo) {
    const disponiveis = veiculos.filter(v => v.cliente === null);
    if (!termo) return disponiveis;
    const t = termo.toLowerCase();
    return disponiveis.filter(v =>
        (v.nome  || "").toLowerCase().includes(t) ||
        (v.marca || "").toLowerCase().includes(t) ||
        (v.placa || "").toLowerCase().includes(t)
    );
}

function filtrarTabelaVeiculos(termo) {
    renderTabelaVeiculos(buscarVeiculos(termo));
}

function filtrarTabelaClientes(termo) {
    renderTabelaClientes(buscarClientes(termo));
}

function filtrarVeiculosAlocacao(termo) {
    renderCardsVeiculos(buscarVeiculosDisponiveis(termo));
}

/*
    NAVEGAÇÃO
*/

function mostrarPagina(paginaId) {
    Object.values(pages).forEach(page => page.style.display = "none");

    const page = pages[paginaId];
    page.style.display = paginaId === "pag-cadastro-veiculos" ? "flex" : "block";

    if (paginaId !== "pag-cadastro-veiculos") {
        fecharFormulario(dom.formVeiculos, dom.tabelaVeiculos);
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

/*
    MODAL (Ver se vai adicionar talvez depois nos formularios de cadastro)
*/

function abrirModalCliente() {
    dom.modalCliente.style.display = "flex";
}

function fecharModal() {
    dom.modalCliente.style.display = "none";
}

/*
    VEÍCULOS
*/

function adicionarCardVeiculo(veiculo) {
    const card = document.createElement("div");
    card.classList.add("card-veiculo");
    card.innerHTML = `
        <img src="${veiculo.img}" alt="${veiculo.nome}">
        <h3>${veiculo.nome}</h3>
        <p>${veiculo.marca}</p>
        <button onclick="selecionarVeiculo(${veiculos.indexOf(veiculo)})">Selecionar</button>
    `;
    dom.containerVeiculos.appendChild(card);
}

function selecionarVeiculo(index) {
    indexVeiculoSelecionado = index;
    abrirModalCliente();
}

function alocarVeiculo() {
    const clienteSelecionado = clientes[dom.selectCliente.value];
    const veiculoSelecionado = veiculos[indexVeiculoSelecionado];

    veiculoSelecionado.cliente = clienteSelecionado;
    alert(`${veiculoSelecionado.nome} alocado para ${clienteSelecionado.nome}.`);

    fecharModal();
    render();
}

/*
    CADASTRO DOS VEÍCULOS
*/

function cadastrarVeiculo() {
    const marca       = document.getElementById("input-marca-veiculo").value.trim();
    const modelo      = document.getElementById("input-modelo-veiculo").value.trim();
    const ano         = document.getElementById("input-ano-veiculo").value.trim();
    const placa       = document.getElementById("input-placa-veiculo").value.trim();
    const renavam     = document.getElementById("input-renavam-veiculo").value.trim();
    const cor         = document.getElementById("input-cor-veiculo").value.trim();
    const km          = document.getElementById("input-km-veiculo").value.trim();
    const combustivel = document.getElementById("input-combustivel-veiculo").value;
    const valorDiario = document.getElementById("input-valor-diario-veiculo").value.trim();
    const status      = document.getElementById("input-status-veiculo").value;
    const imagem      = document.getElementById("input-imagem-veiculo").value.trim();

    if (!marca || !modelo || !placa || !renavam) return;

    veiculos.push({
        nome: modelo, marca, ano, placa, renavam, cor,
        kms_atual: km, combustivel, valor_diario: valorDiario,
        status, img: imagem, cliente: null
    });

    dom.formVeiculos.reset();
    fecharFormularioVeiculo();
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

    dom.formClientes.reset();
    fecharFormularioCliente();
    render();
}

/*
    FORMULÁRIOS DE CADASTRO
*/

function alternarFormulario(form, tabela) {
    const abrindo    = form.style.display !== "block";
    form.style.display   = abrindo ? "block" : "none";
    tabela.style.display = abrindo ? "none"  : "block";
}

function fecharFormulario(form, tabela) {
    if (form) {
        form.style.display   = "none";
        tabela.style.display = "block";
    }
}

function alternarFormularioVeiculo() {
    if (dom.formVeiculos) alternarFormulario(dom.formVeiculos, dom.tabelaVeiculos);
}

function fecharFormularioVeiculo() {
    fecharFormulario(dom.formVeiculos, dom.tabelaVeiculos);
}

function alternarFormularioCliente() {
    if (dom.formClientes) alternarFormulario(dom.formClientes, dom.tabelaClientes);
}

function fecharFormularioCliente() {
    fecharFormulario(dom.formClientes, dom.tabelaClientes);
}
