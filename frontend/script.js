/*
    DOM
*/

const pages = {
    "pag-dashboard": document.getElementById("pag-dashboard"),
    "pag-alocacao": document.getElementById("pag-alocacao"),
    "pag-cadastro-veiculos": document.getElementById("pag-cadastro-veiculos"),
    "pag-cadastro-clientes": document.getElementById("pag-cadastro-clientes"),
    "pag-cadastro-funcionarios": document.getElementById("pag-cadastro-funcionarios")
};

const dom = {
    loginScreen: document.getElementById("login-screen"),
    layout: document.getElementById("layout"),
    actionBarTitle: document.getElementById("action-bar-title"),
    actionBarActions: document.getElementById("action-bar-actions"),
    // veículos
    tbodyVeiculos: document.getElementById("tbody-veiculos"),
    tabelaVeiculos: document.getElementById("tabela-veiculos"),
    formVeiculos: document.getElementById("form-cadastrar-veiculo"),
    // clientes
    tbodyClientes: document.getElementById("tbody-clientes"),
    tabelaClientes: document.getElementById("tabela-clientes"),
    formClientes: document.getElementById("form-cadastrar-clientes"),
    // funcionários
    tbodyFuncionarios: document.getElementById("tbody-funcionarios"),
    tabelaFuncionarios: document.getElementById("tabela-funcionarios"),
    formFuncionarios: document.getElementById("form-cadastrar-funcionario"),
    // dashboard
    statTotalVeiculos: document.getElementById("stat-total-veiculos"),
    statDisponiveis: document.getElementById("stat-veiculos-disponiveis"),
    statAlocados: document.getElementById("stat-veiculos-alocados"),
    statTotalClientes: document.getElementById("stat-total-clientes"),
    listaAlocacoes: document.getElementById("lista-alocacoes"),
};

/*
    DADOS
*/

let veiculos = [
    {
        id: 1, nome: "Corolla", renavam: "12345678901", marca: "Toyota",
        ano: 2022, placa: "ABC-1234", img: "resources/veiculos/corolla.png",
        status: "Disponível", cor: "Branco Pérola", combustivel: "Flex",
        km_atual: 45000, valor_diario: 180.00, cliente: null
    },
    {
        id: 2, nome: "Civic", renavam: "98765432109", marca: "Honda",
        ano: 2021, placa: "DEF-5678", img: "resources/veiculos/civic.png",
        status: "Alugado", cor: "Cinza Metálico", combustivel: "Gasolina",
        km_atual: 32000, valor_diario: 200.00,
        cliente: { nome: "Bruno Alcantara", cpf: "123.456.789-01" }
    },
    {
        id: 3, nome: "Duster", renavam: "45678912345", marca: "Renault",
        ano: 2023, placa: "GHI-9012", img: "resources/veiculos/duster.png",
        status: "Em manutenção", cor: "Prata", combustivel: "Flex",
        km_atual: 18000, valor_diario: 220.00, cliente: null
    }
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

let funcionarios = [
    { id: 1, nome: "Ana Silva", cpf: "111.222.333-44", email: "ana@email.com", telefone: "(45) 99111-2222", cargo: "Atendente", salario: 2500.00, admissao: "2023-01-15" },
    { id: 2, nome: "Carlos Souza", cpf: "222.333.444-55", email: "carlos@email.com", telefone: "(45) 99333-4444", cargo: "Gerente", salario: 5000.00, admissao: "2022-03-10" },
    { id: 3, nome: "Mariana Costa", cpf: "333.444.555-66", email: "mariana@email.com", telefone: "(45) 99555-6666", cargo: "Atendente", salario: 2500.00, admissao: "2023-06-20" }
];

let locacoes = [
    {
        id: 1,
        idx_veiculo: 1,
        idx_cliente: 0,
        idx_funcionario: 0,
        data_saida: "2026-06-25",
        data_retorno_prevista: "2026-07-02",
        km_saida: 32000,
        status: "Ativo",
        forma_pagamento: "PIX",
        valor_entrada: 200.00
    }
];

const estadosCidades = {
    "AC": ["Rio Branco", "Cruzeiro do Sul", "Sena Madureira"],
    "AL": ["Maceió", "Arapiraca", "Palmeira dos Índios"],
    "AM": ["Manaus", "Parintins", "Itacoatiara"],
    "AP": ["Macapá", "Santana", "Laranjal do Jari"],
    "BA": ["Salvador", "Feira de Santana", "Vitória da Conquista", "Camaçari"],
    "CE": ["Fortaleza", "Caucaia", "Juazeiro do Norte", "Maracanaú"],
    "DF": ["Brasília", "Ceilândia", "Taguatinga"],
    "ES": ["Vitória", "Vila Velha", "Serra", "Cariacica"],
    "GO": ["Goiânia", "Aparecida de Goiânia", "Anápolis"],
    "MA": ["São Luís", "Imperatriz", "Timon", "Caxias"],
    "MG": ["Belo Horizonte", "Uberlândia", "Contagem", "Juiz de Fora", "Montes Claros"],
    "MS": ["Campo Grande", "Dourados", "Três Lagoas"],
    "MT": ["Cuiabá", "Várzea Grande", "Rondonópolis"],
    "PA": ["Belém", "Ananindeua", "Santarém", "Marabá"],
    "PB": ["João Pessoa", "Campina Grande", "Santa Rita", "Patos"],
    "PE": ["Recife", "Caruaru", "Petrolina", "Olinda"],
    "PI": ["Teresina", "Parnaíba", "Picos"],
    "PR": ["Curitiba", "Londrina", "Maringá", "Cascavel", "Foz do Iguaçu", "Ponta Grossa"],
    "RJ": ["Rio de Janeiro", "Niterói", "Nova Iguaçu", "São Gonçalo"],
    "RN": ["Natal", "Mossoró", "Parnamirim"],
    "RO": ["Porto Velho", "Ji-Paraná", "Ariquemes"],
    "RR": ["Boa Vista", "Rorainópolis"],
    "RS": ["Porto Alegre", "Caxias do Sul", "Pelotas", "Canoas", "Santa Maria"],
    "SC": ["Florianópolis", "Joinville", "Blumenau", "São José", "Chapecó"],
    "SE": ["Aracaju", "Nossa Senhora do Socorro", "Lagarto"],
    "SP": ["São Paulo", "Campinas", "Santos", "Ribeirão Preto", "São José dos Campos", "Sorocaba"],
    "TO": ["Palmas", "Araguaína", "Gurupi"]
};

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
    },
    "pag-cadastro-funcionarios": {
        title: "Funcionários",
        actions: [
            { label: "Novo Funcionário", onclick: "alternarFormularioFuncionario()", primary: true }
        ]
    }
};

/*
    LOGIN
*/

function fazerLogin(event) {
    event.preventDefault();
    const usuario = document.getElementById("input-login-usuario").value.trim();
    const senha = document.getElementById("input-login-senha").value.trim();
    const erro = document.getElementById("login-erro");

    if (!usuario || !senha) {
        erro.textContent = "Preencha todos os campos.";
        return;
    }

    erro.textContent = "";
    dom.loginScreen.style.display = "none";
    dom.layout.style.display = "flex";
    init();
}

/*
    LOGOUT
*/

function fazerLogout() {
    if (confirm("Tem certeza que deseja sair?")) {
        localStorage.removeItem("usuarioLogado");
        sessionStorage.clear();

        dom.layout.style.display = "none";
        dom.loginScreen.style.display = "flex";

        document.getElementById("form-login").reset();
        document.getElementById("login-erro").textContent = "";
        document.getElementById("input-login-usuario").focus();
    }
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
    renderSecaoDisponiveis();
    renderSecaoReservados();
    renderDashboard();
    renderTabelaVeiculos();
    renderTabelaClientes();
    renderTabelaFuncionarios();
}

function limparBuscas() {
    ["busca-alocacao", "busca-veiculos", "busca-clientes", "busca-funcionarios"].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.value = "";
    });
}

function alternarVisaoAlocacao(view) {
    document.querySelectorAll(".alocacao-toggle-btn").forEach(btn => {
        btn.classList.toggle("active", btn.dataset.view === view);
    });

    document.querySelectorAll(".alocacao-view-panel").forEach(panel => {
        panel.classList.toggle("active", panel.id === `alocacao-view-${view}`);
    });
}

/*
    ALOCAÇÃO — SEÇÃO DISPONÍVEIS
*/

function renderSecaoDisponiveis(dados) {
    const lista = dados !== undefined ? dados : veiculos.filter(v => veiculoDisponivel(v));
    const container = document.getElementById("alocacao-disponiveis");
    const msg = document.getElementById("msg-sem-disponiveis");
    if (!container) return;
    container.innerHTML = "";
    if (msg) msg.style.display = lista.length === 0 ? "block" : "none";
    lista.forEach(v => {
        const idx = veiculos.indexOf(v);
        container.appendChild(criarCardDisponivel(v, idx));
    });
}

function criarCardDisponivel(v, idx) {
    const card = document.createElement("div");
    card.className = "card-veiculo";
    card.innerHTML = `
        <div class="card-img-wrapper">
            <img src="${v.img || ""}" alt="${v.nome || "Veículo"}">
        </div>
        <div class="card-info-list">
            <div class="card-info-row"><span class="card-info-label">Marca</span><span class="card-info-val">${v.marca || "—"}</span></div>
            <div class="card-info-row"><span class="card-info-label">Modelo</span><span class="card-info-val">${v.nome || "—"} ${v.ano ? "(" + v.ano + ")" : ""}</span></div>
            <div class="card-info-row"><span class="card-info-label">Placa</span><span class="card-info-val">${v.placa || "—"}</span></div>
            <div class="card-info-row"><span class="card-info-label">Cor</span><span class="card-info-val">${v.cor || "—"}</span></div>
            <div class="card-info-row"><span class="card-info-label">Combustível</span><span class="card-info-val">${v.combustivel || "—"}</span></div>
            <div class="card-info-row"><span class="card-info-label">KM Atual</span><span class="card-info-val">${v.km_atual != null ? v.km_atual.toLocaleString("pt-BR") + " km" : "—"}</span></div>
            <div class="card-info-row card-info-preco"><span class="card-info-label">Valor Diário</span><span class="card-info-val">R$ ${v.valor_diario != null ? formatarMoeda(v.valor_diario) : "—"}</span></div>
        </div>
        <button class="btn-selecionar btn-alocar" onclick="selecionarVeiculo(${idx})">Alocar Veículo</button>
    `;
    return card;
}

/*
    ALOCAÇÃO — SEÇÃO RESERVADOS
*/

function renderSecaoReservados(dados) {
    const lista = dados !== undefined ? dados : veiculos.filter(v => !veiculoDisponivel(v));
    const container = document.getElementById("alocacao-reservados");
    const msg = document.getElementById("msg-sem-reservados");
    if (!container) return;
    container.innerHTML = "";
    if (msg) msg.style.display = lista.length === 0 ? "block" : "none";
    lista.forEach(v => {
        const idx = veiculos.indexOf(v);
        container.appendChild(criarCardReservado(v, idx));
    });
}

function criarCardReservado(v, idx) {
    const locacao = locacoes.find(l => l.idx_veiculo === idx);
    const clienteNome = locacao ? (clientes[locacao.idx_cliente]?.nome || "—") : "—";
    const dataSaida = locacao ? formatarData(locacao.data_saida) : "—";
    const dataRetorno = locacao ? formatarData(locacao.data_retorno_prevista) : "—";
    const kmSaida = locacao ? locacao.km_saida.toLocaleString("pt-BR") + " km" : "—";
    const statusLoc = locacao ? locacao.status : (v.status || "—");

    const btnHtml = locacao
        ? `<button class="btn-selecionar btn-finalizar" onclick="abrirFormFinalizacao(${idx})">Finalizar Locação</button>`
        : `<button class="btn-selecionar" style="background:#aaa;cursor:default" disabled>${v.status}</button>`;

    const card = document.createElement("div");
    card.className = "card-veiculo";
    card.innerHTML = `
        <div class="card-img-wrapper">
            <img src="${v.img || ""}" alt="${v.nome || "Veículo"}">
        </div>
        <div class="card-info-list">
            <div class="card-info-row"><span class="card-info-label">Marca</span><span class="card-info-val">${v.marca || "—"}</span></div>
            <div class="card-info-row"><span class="card-info-label">Modelo</span><span class="card-info-val">${v.nome || "—"}</span></div>
            <div class="card-info-row"><span class="card-info-label">Placa</span><span class="card-info-val">${v.placa || "—"}</span></div>
            <div class="card-info-row"><span class="card-info-label">Cliente</span><span class="card-info-val">${clienteNome}</span></div>
            <div class="card-info-row"><span class="card-info-label">Data Saída</span><span class="card-info-val">${dataSaida}</span></div>
            <div class="card-info-row"><span class="card-info-label">Retorno Previsto</span><span class="card-info-val">${dataRetorno}</span></div>
            <div class="card-info-row"><span class="card-info-label">KM Saída</span><span class="card-info-val">${kmSaida}</span></div>
            <div class="card-info-row"><span class="card-info-label">Status</span><span class="card-info-val">${statusLoc}</span></div>
        </div>
        ${btnHtml}
    `;
    return card;
}

function renderDashboard() {
    const alocados = veiculos.filter(v => v.cliente !== null);
    const disponiveis = veiculos.filter(v => veiculoDisponivel(v));

    dom.statTotalVeiculos.textContent = veiculos.length;
    dom.statDisponiveis.textContent = disponiveis.length;
    dom.statAlocados.textContent = alocados.length;
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
        const statusTexto = v.cliente ? "Alugado" : (v.status || "Disponível");
        const clienteTexto = v.cliente ? v.cliente.nome : "—";
        return `
            <tr>
                <td>${v.nome || "—"}</td>
                <td>${v.marca || "—"}</td>
                <td>${v.placa || "—"}</td>
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
        dom.tbodyClientes.innerHTML = '<tr><td colspan="4" class="empty-state">Nenhum cliente encontrado.</td></tr>';
        return;
    }

    dom.tbodyClientes.innerHTML = lista.map(c => `
        <tr>
            <td>${c.nome || "—"}</td>
            <td>${c.cpf || "—"}</td>
            <td>${c.email || "—"}</td>
            <td>${c.telefone || "—"}</td>
        </tr>
    `).join("");
}

function renderTabelaFuncionarios(dados) {
    if (!dom.tbodyFuncionarios) return;
    const lista = dados !== undefined ? dados : funcionarios;

    if (lista.length === 0) {
        dom.tbodyFuncionarios.innerHTML = '<tr><td colspan="7" class="empty-state">Nenhum funcionário encontrado.</td></tr>';
        return;
    }

    dom.tbodyFuncionarios.innerHTML = lista.map(f => `
        <tr>
            <td>${f.nome || "—"}</td>
            <td>${f.cpf || "—"}</td>
            <td>${f.email || "—"}</td>
            <td>${f.telefone || "—"}</td>
            <td>${f.cargo || "—"}</td>
            <td>${f.salario != null ? "R$ " + formatarMoeda(f.salario) : "—"}</td>
            <td>
                <button class="btn-action outline" onclick="deletarFuncionario(${funcionarios.indexOf(f)})" style="font-size:11px;padding:4px 10px;color:#c0392b;border-color:#f5c6cb">Excluir</button>
            </td>
        </tr>
    `).join("");
}

/*
    BUSCA / FILTRO
*/

function buscarVeiculos(termo) {
    if (!termo) return veiculos;
    const t = termo.toLowerCase();
    return veiculos.filter(v =>
        (v.nome || "").toLowerCase().includes(t) ||
        (v.marca || "").toLowerCase().includes(t) ||
        (v.placa || "").toLowerCase().includes(t) ||
        (v.status || "").toLowerCase().includes(t)
    );
}

function buscarClientes(termo) {
    if (!termo) return clientes;
    const t = termo.toLowerCase();
    return clientes.filter(c =>
        (c.nome || "").toLowerCase().includes(t) ||
        (c.cpf || "").toLowerCase().includes(t) ||
        (c.email || "").toLowerCase().includes(t)
    );
}

function filtrarTabelaVeiculos(termo) {
    renderTabelaVeiculos(buscarVeiculos(termo));
}

function filtrarTabelaClientes(termo) {
    renderTabelaClientes(buscarClientes(termo));
}

function filtrarVeiculosAlocacao(termo) {
    const resultado = buscarVeiculos(termo);
    renderSecaoDisponiveis(resultado.filter(v => veiculoDisponivel(v)));
    renderSecaoReservados(resultado.filter(v => !veiculoDisponivel(v)));
}

function filtrarTabelaFuncionarios(termo) {
    if (!termo) {
        renderTabelaFuncionarios();
        return;
    }
    const t = termo.toLowerCase();
    const filtrados = funcionarios.filter(f =>
        (f.nome || "").toLowerCase().includes(t) ||
        (f.cpf || "").toLowerCase().includes(t) ||
        (f.email || "").toLowerCase().includes(t) ||
        (f.cargo || "").toLowerCase().includes(t)
    );
    renderTabelaFuncionarios(filtrados);
}

/*
    NAVEGAÇÃO
*/

function mostrarPagina(paginaId) {
    voltarParaVeiculos();

    Object.values(pages).forEach(page => { if (page) page.style.display = "none"; });

    const page = pages[paginaId];
    if (!page) return;

    const flexPages = ["pag-cadastro-veiculos", "pag-cadastro-funcionarios"];
    page.style.display = flexPages.includes(paginaId) ? "flex" : "block";

    if (paginaId !== "pag-cadastro-veiculos") {
        fecharFormulario(dom.formVeiculos, dom.tabelaVeiculos);
    }
    if (paginaId !== "pag-cadastro-funcionarios") {
        fecharFormulario(dom.formFuncionarios, dom.tabelaFuncionarios);
    }

    document.querySelectorAll(".nav-item").forEach(item => {
        item.classList.toggle("active", item.dataset.page === paginaId);
    });

    const config = actionBarConfig[paginaId];
    if (!config) return;
    dom.actionBarTitle.textContent = config.title;
    dom.actionBarActions.innerHTML = config.actions.map(action =>
        `<button class="btn-action${action.primary ? "" : " outline"}" onclick="${action.onclick}">${action.label}</button>`
    ).join("");
}

/*
    HELPERS DE STATUS
*/

function veiculoDisponivel(v) {
    return v.cliente === null && v.status === "Disponível";
}

function getStatusVeiculo(v) {
    if (v.cliente !== null) return "Alugado";
    return v.status || "Disponível";
}

function getBadgeClass(status) {
    switch (status) {
        case "Disponível": return "badge-disponivel";
        case "Alugado": return "badge-alugado";
        case "Em manutenção": return "badge-manutencao";
        default: return "badge-disponivel";
    }
}

/*
    SELEÇÃO DO VEÍCULO → PAINEL DE ALOCAÇÃO
*/

function selecionarVeiculo(index) {
    const v = veiculos[index];

    if (!veiculoDisponivel(v)) {
        alert(`Este veículo está "${getStatusVeiculo(v)}" e não pode ser alocado.`);
        return;
    }

    indexVeiculoSelecionado = index;
    mostrarPainelAlocacao(v);
}

function mostrarPainelAlocacao(v) {
    document.getElementById("det-img").src = v.img || "";
    document.getElementById("det-img").alt = v.nome || "Veículo";
    document.getElementById("det-marca").textContent = v.marca || "—";
    document.getElementById("det-modelo").textContent = v.nome || "—";
    document.getElementById("det-ano").textContent = v.ano || "—";
    document.getElementById("det-combustivel").textContent = v.combustivel || "—";
    document.getElementById("det-placa").textContent = v.placa || "—";
    document.getElementById("det-cor").textContent = v.cor || "—";
    document.getElementById("det-km").textContent = v.km_atual != null
        ? v.km_atual.toLocaleString("pt-BR") + " km"
        : "—";
    document.getElementById("det-valor-diario").textContent = v.valor_diario != null
        ? "R$ " + formatarMoeda(v.valor_diario)
        : "—";

    const badge = document.getElementById("det-status-badge");
    badge.textContent = getStatusVeiculo(v);
    badge.className = "det-badge " + getBadgeClass(getStatusVeiculo(v));

    document.getElementById("alocacao-veiculo-titulo").textContent =
        `${v.marca || ""} ${v.nome || ""} — ${v.placa || ""}`.trim();

    const kmInput = document.getElementById("alocar-km-saida");
    kmInput.value = v.km_atual || "";
    kmInput.min = v.km_atual || 0;

    const hoje = new Date().toISOString().split("T")[0];
    document.getElementById("alocar-data-saida").min = hoje;
    document.getElementById("alocar-data-retorno").min = hoje;

    popularSelectClientes();
    popularSelectFuncionarios();

    limparFormAlocacao();
    calcularResumo();

    document.getElementById("alocacao-selecao").style.display = "none";
    document.getElementById("alocacao-finalizar-wrapper").style.display = "none";
    document.getElementById("alocacao-form-wrapper").style.display = "block";
}

function voltarParaVeiculos() {
    const wrapper = document.getElementById("alocacao-form-wrapper");
    const finalizar = document.getElementById("alocacao-finalizar-wrapper");
    const selecao = document.getElementById("alocacao-selecao");
    if (wrapper) wrapper.style.display = "none";
    if (finalizar) finalizar.style.display = "none";
    if (selecao) selecao.style.display = "block";
    indexVeiculoSelecionado = null;
}

/*
    POPULAÇÃO DOS SELECTS
*/

function popularSelectClientes() {
    const sel = document.getElementById("alocar-cliente");
    sel.innerHTML = '<option value="">Selecione o cliente</option>';
    clientes.forEach((c, i) => {
        const opt = document.createElement("option");
        opt.value = i;
        opt.textContent = `${c.nome} — ${c.cpf}`;
        sel.appendChild(opt);
    });
}

function popularSelectFuncionarios() {
    const sel = document.getElementById("alocar-funcionario");
    sel.innerHTML = '<option value="">Selecione o funcionário</option>';
    funcionarios.forEach((f, i) => {
        const opt = document.createElement("option");
        opt.value = i;
        opt.textContent = `${f.nome} — ${f.cargo}`;
        sel.appendChild(opt);
    });
}

/*
    CÁLCULO DO RESUMO (nova alocação)
*/

function calcularResumo() {
    const v = indexVeiculoSelecionado !== null ? veiculos[indexVeiculoSelecionado] : null;
    if (!v) return;

    const dataSaida = document.getElementById("alocar-data-saida").value;
    const dataRetorno = document.getElementById("alocar-data-retorno").value;

    document.getElementById("res-veiculo").textContent = `${v.marca || ""} ${v.nome || ""}`.trim() || "—";
    document.getElementById("res-valor-diario").textContent = v.valor_diario != null
        ? "R$ " + formatarMoeda(v.valor_diario)
        : "—";

    if (dataSaida && dataRetorno) {
        const [y1, m1, d1] = dataSaida.split("-").map(Number);
        const [y2, m2, d2] = dataRetorno.split("-").map(Number);
        const ts1 = new Date(y1, m1 - 1, d1);
        const ts2 = new Date(y2, m2 - 1, d2);
        const dias = Math.ceil((ts2 - ts1) / (1000 * 60 * 60 * 24));

        if (dias > 0) {
            const total = dias * (v.valor_diario || 0);
            document.getElementById("res-periodo").textContent = `${formatarData(dataSaida)} → ${formatarData(dataRetorno)}`;
            document.getElementById("res-dias").textContent = `${dias} dia${dias > 1 ? "s" : ""}`;
            document.getElementById("res-total").textContent = "R$ " + formatarMoeda(total);
        } else {
            document.getElementById("res-periodo").textContent = "Datas inválidas";
            document.getElementById("res-dias").textContent = "—";
            document.getElementById("res-total").textContent = "—";
        }
    } else {
        document.getElementById("res-periodo").textContent = "—";
        document.getElementById("res-dias").textContent = "—";
        document.getElementById("res-total").textContent = "—";
    }
}

/*
    VALIDAÇÃO DO FORMULÁRIO DE ALOCAÇÃO
*/

function limparFormAlocacao() {
    const form = document.getElementById("form-alocacao");
    if (form) form.reset();

    document.querySelectorAll("#form-alocacao .campo-erro").forEach(el => { el.textContent = ""; });
    document.querySelectorAll("#form-alocacao .campo-invalido").forEach(el => { el.classList.remove("campo-invalido"); });

    const notif = document.getElementById("notif-alocacao");
    if (notif) notif.style.display = "none";
}

function validarFormAlocacao() {
    let valido = true;

    const campos = [
        { id: "alocar-cliente", erroId: "erro-cliente", msg: "Selecione um cliente." },
        { id: "alocar-funcionario", erroId: "erro-funcionario", msg: "Selecione um funcionário responsável." },
        { id: "alocar-data-saida", erroId: "erro-data-saida", msg: "Informe a data de saída." },
        { id: "alocar-data-retorno", erroId: "erro-data-retorno", msg: "Informe a data de retorno prevista." },
        { id: "alocar-km-saida", erroId: "erro-km-saida", msg: "Informe a quilometragem de saída." },
        { id: "alocar-status", erroId: "erro-status", msg: "Selecione o status da locação." },
        { id: "alocar-pagamento", erroId: "erro-pagamento", msg: "Selecione a forma de pagamento." },
    ];

    campos.forEach(c => {
        const el = document.getElementById(c.id);
        const erro = document.getElementById(c.erroId);
        if (el) el.classList.remove("campo-invalido");
        if (erro) erro.textContent = "";
    });

    campos.forEach(c => {
        const el = document.getElementById(c.id);
        if (!el || !el.value.trim()) {
            if (el) el.classList.add("campo-invalido");
            const erro = document.getElementById(c.erroId);
            if (erro) erro.textContent = c.msg;
            valido = false;
        }
    });

    const dataSaida = document.getElementById("alocar-data-saida").value;
    const dataRetorno = document.getElementById("alocar-data-retorno").value;
    const hoje = new Date().toISOString().split("T")[0];

    if (dataSaida && dataSaida < hoje) {
        document.getElementById("alocar-data-saida").classList.add("campo-invalido");
        document.getElementById("erro-data-saida").textContent = "A data de saída não pode ser no passado.";
        valido = false;
    }

    if (dataSaida && dataRetorno && dataRetorno <= dataSaida) {
        document.getElementById("alocar-data-retorno").classList.add("campo-invalido");
        document.getElementById("erro-data-retorno").textContent = "A data de retorno deve ser posterior à data de saída.";
        valido = false;
    }

    const v = veiculos[indexVeiculoSelecionado];
    const kmVal = parseInt(document.getElementById("alocar-km-saida").value, 10);
    if (v && v.km_atual != null && !isNaN(kmVal) && kmVal < v.km_atual) {
        document.getElementById("alocar-km-saida").classList.add("campo-invalido");
        document.getElementById("erro-km-saida").textContent =
            `KM de saída deve ser igual ou maior que ${v.km_atual.toLocaleString("pt-BR")} km.`;
        valido = false;
    }

    return valido;
}

/*
    CONFIRMAR ALOCAÇÃO
*/

function confirmarAlocacao(event) {
    event.preventDefault();

    if (!validarFormAlocacao()) {
        mostrarNotificacao("Corrija os campos indicados antes de continuar.", "erro");
        document.getElementById("notif-alocacao").scrollIntoView({ behavior: "smooth", block: "nearest" });
        return;
    }

    const v = veiculos[indexVeiculoSelecionado];
    const idxCliente = parseInt(document.getElementById("alocar-cliente").value, 10);
    const idxFuncionario = parseInt(document.getElementById("alocar-funcionario").value, 10);
    const clienteSel = clientes[idxCliente];
    const dataSaida = document.getElementById("alocar-data-saida").value;
    const dataRetorno = document.getElementById("alocar-data-retorno").value;
    const kmSaida = parseInt(document.getElementById("alocar-km-saida").value, 10);
    const status = document.getElementById("alocar-status").value;
    const pagamento = document.getElementById("alocar-pagamento").value;
    const entrada = parseFloat(document.getElementById("alocar-entrada").value) || 0;

    locacoes.push({
        id: locacoes.length + 1,
        idx_veiculo: indexVeiculoSelecionado,
        idx_cliente: idxCliente,
        idx_funcionario: idxFuncionario,
        data_saida: dataSaida,
        data_retorno_prevista: dataRetorno,
        km_saida: kmSaida,
        status: status,
        forma_pagamento: pagamento,
        valor_entrada: entrada
    });

    v.status = "Alugado";
    v.cliente = clienteSel;

    mostrarNotificacao(
        `Veículo ${v.marca} ${v.nome} alocado com sucesso para ${clienteSel.nome}!`,
        "sucesso"
    );

    setTimeout(() => {
        render();
        voltarParaVeiculos();
    }, 1800);
}

/*
    NOTIFICAÇÕES
*/

function mostrarNotificacao(msg, tipo) {
    const notif = document.getElementById("notif-alocacao");
    if (!notif) return;
    notif.textContent = msg;
    notif.className = `notif ${tipo}`;
    notif.style.display = "block";

    if (tipo === "sucesso") {
        setTimeout(() => { notif.style.display = "none"; }, 3000);
    }
}

function mostrarNotificacaoFinalizar(msg, tipo) {
    const notif = document.getElementById("notif-finalizar");
    if (!notif) return;
    notif.textContent = msg;
    notif.className = `notif ${tipo}`;
    notif.style.display = "block";

    if (tipo === "sucesso") {
        setTimeout(() => { notif.style.display = "none"; }, 3000);
    }
}

/*
    FINALIZAÇÃO DE LOCAÇÃO
*/

function abrirFormFinalizacao(idxVeiculo) {
    const v = veiculos[idxVeiculo];
    const locacao = locacoes.find(l => l.idx_veiculo === idxVeiculo);

    if (!locacao) {
        alert("Nenhuma locação ativa encontrada para este veículo.");
        return;
    }

    indexVeiculoSelecionado = idxVeiculo;

    document.getElementById("fin-img").src = v.img || "";
    document.getElementById("fin-img").alt = v.nome || "";
    document.getElementById("fin-marca").textContent = v.marca || "—";
    document.getElementById("fin-modelo").textContent = v.nome || "—";
    document.getElementById("fin-placa").textContent = v.placa || "—";
    document.getElementById("fin-cliente").textContent = clientes[locacao.idx_cliente]?.nome || "—";
    document.getElementById("fin-data-saida").textContent = formatarData(locacao.data_saida);
    document.getElementById("fin-data-retorno-prev").textContent = formatarData(locacao.data_retorno_prevista);
    document.getElementById("fin-km-saida").textContent = locacao.km_saida.toLocaleString("pt-BR") + " km";
    document.getElementById("fin-status").textContent = locacao.status;
    document.getElementById("fin-valor-diario").textContent = "R$ " + formatarMoeda(v.valor_diario || 0);
    document.getElementById("finalizar-veiculo-titulo").textContent =
        `${v.marca || ""} ${v.nome || ""} — ${v.placa || ""}`.trim();

    const dataRetornInput = document.getElementById("fin-data-retorno-real");
    dataRetornInput.min = locacao.data_saida;
    dataRetornInput.value = "";

    const kmInput = document.getElementById("fin-km-retorno");
    kmInput.min = locacao.km_saida;
    kmInput.value = "";

    document.getElementById("form-finalizar").reset();
    document.getElementById("notif-finalizar").style.display = "none";
    document.getElementById("fin-res-atraso-linha").style.display = "none";
    document.getElementById("fin-res-extra-linha").style.display = "none";
    ["fin-res-periodo", "fin-res-dias", "fin-res-base", "fin-res-total"].forEach(id => {
        document.getElementById(id).textContent = "—";
    });

    document.getElementById("alocacao-selecao").style.display = "none";
    document.getElementById("alocacao-form-wrapper").style.display = "none";
    document.getElementById("alocacao-finalizar-wrapper").style.display = "block";
}

function calcularCustoFinalizacao() {
    const v = indexVeiculoSelecionado !== null ? veiculos[indexVeiculoSelecionado] : null;
    const locacao = v ? locacoes.find(l => l.idx_veiculo === indexVeiculoSelecionado) : null;
    if (!v || !locacao) return;

    const dataRetornoReal = document.getElementById("fin-data-retorno-real").value;
    const custoAdicional = parseFloat(document.getElementById("fin-custo-adicional").value) || 0;

    if (!dataRetornoReal) {
        ["fin-res-periodo", "fin-res-dias", "fin-res-base", "fin-res-total"].forEach(id => {
            document.getElementById(id).textContent = "—";
        });
        document.getElementById("fin-res-atraso-linha").style.display = "none";
        document.getElementById("fin-res-extra-linha").style.display = "none";
        return;
    }

    const [y1, m1, d1] = locacao.data_saida.split("-").map(Number);
    const [y2, m2, d2] = dataRetornoReal.split("-").map(Number);
    const tsInicio = new Date(y1, m1 - 1, d1);
    const tsFim = new Date(y2, m2 - 1, d2);
    const dias = Math.ceil((tsFim - tsInicio) / (1000 * 60 * 60 * 24));

    if (dias <= 0) {
        document.getElementById("fin-res-periodo").textContent = "Data inválida";
        ["fin-res-dias", "fin-res-base", "fin-res-total"].forEach(id => {
            document.getElementById(id).textContent = "—";
        });
        return;
    }

    const valorDiario = v.valor_diario || 0;
    const valorBase = dias * valorDiario;

    const [y3, m3, d3] = locacao.data_retorno_prevista.split("-").map(Number);
    const tsPrevisto = new Date(y3, m3 - 1, d3);
    const diasAtraso = Math.max(0, Math.ceil((tsFim - tsPrevisto) / (1000 * 60 * 60 * 24)));
    const multa = diasAtraso > 0 ? diasAtraso * valorDiario * 0.5 : 0;

    const total = valorBase + multa + custoAdicional;

    document.getElementById("fin-res-periodo").textContent = `${formatarData(locacao.data_saida)} → ${formatarData(dataRetornoReal)}`;
    document.getElementById("fin-res-dias").textContent = `${dias} dia${dias > 1 ? "s" : ""}`;
    document.getElementById("fin-res-base").textContent = "R$ " + formatarMoeda(valorBase);

    if (diasAtraso > 0) {
        document.getElementById("fin-res-atraso-linha").style.display = "flex";
        document.getElementById("fin-res-multa").textContent = `R$ ${formatarMoeda(multa)} (${diasAtraso} dia${diasAtraso > 1 ? "s" : ""} de atraso)`;
    } else {
        document.getElementById("fin-res-atraso-linha").style.display = "none";
    }

    if (custoAdicional > 0) {
        document.getElementById("fin-res-extra-linha").style.display = "flex";
        document.getElementById("fin-res-extra").textContent = "R$ " + formatarMoeda(custoAdicional);
    } else {
        document.getElementById("fin-res-extra-linha").style.display = "none";
    }

    document.getElementById("fin-res-total").textContent = "R$ " + formatarMoeda(total);
}

function confirmarFinalizacao(event) {
    event.preventDefault();

    const v = veiculos[indexVeiculoSelecionado];
    const locacao = locacoes.find(l => l.idx_veiculo === indexVeiculoSelecionado);

    const kmRetorno = document.getElementById("fin-km-retorno").value;
    const dataRetornoReal = document.getElementById("fin-data-retorno-real").value;
    let valido = true;

    document.getElementById("fin-km-retorno").classList.remove("campo-invalido");
    document.getElementById("erro-fin-km").textContent = "";
    document.getElementById("fin-data-retorno-real").classList.remove("campo-invalido");
    document.getElementById("erro-fin-data").textContent = "";

    if (!kmRetorno) {
        document.getElementById("fin-km-retorno").classList.add("campo-invalido");
        document.getElementById("erro-fin-km").textContent = "Informe o KM de retorno.";
        valido = false;
    }

    if (!dataRetornoReal) {
        document.getElementById("fin-data-retorno-real").classList.add("campo-invalido");
        document.getElementById("erro-fin-data").textContent = "Informe a data de retorno real.";
        valido = false;
    }

    if (!valido) return;

    const kmVal = parseInt(kmRetorno, 10);
    if (locacao && kmVal < locacao.km_saida) {
        document.getElementById("fin-km-retorno").classList.add("campo-invalido");
        document.getElementById("erro-fin-km").textContent =
            `KM de retorno deve ser ≥ KM de saída (${locacao.km_saida.toLocaleString("pt-BR")} km).`;
        return;
    }

    v.status = "Disponível";
    v.cliente = null;
    v.km_atual = kmVal;

    const locIdx = locacoes.findIndex(l => l.idx_veiculo === indexVeiculoSelecionado);
    if (locIdx !== -1) locacoes.splice(locIdx, 1);

    mostrarNotificacaoFinalizar(`Locação do ${v.marca} ${v.nome} finalizada com sucesso!`, "sucesso");

    setTimeout(() => {
        render();
        voltarParaVeiculos();
    }, 1800);
}

/*
    CADASTRO DOS VEÍCULOS
*/

function cadastrarVeiculo() {
    const marca = document.getElementById("input-marca-veiculo").value.trim();
    const modelo = document.getElementById("input-modelo-veiculo").value.trim();
    const ano = document.getElementById("input-ano-veiculo").value.trim();
    const placa = document.getElementById("input-placa-veiculo").value.trim();
    const renavam = document.getElementById("input-renavam-veiculo").value.trim();
    const cor = document.getElementById("input-cor-veiculo").value.trim();
    const km = document.getElementById("input-km-veiculo").value.trim();
    const combustivel = document.getElementById("input-combustivel-veiculo").value;
    const valorDiario = document.getElementById("input-valor-diario-veiculo").value.trim();
    const status = document.getElementById("input-status-veiculo").value;
    const imagem = document.getElementById("input-imagem-veiculo").value.trim();

    if (!marca || !modelo || !placa || !renavam) return;

    veiculos.push({
        id: veiculos.length + 1,
        nome: modelo, marca, ano: ano ? parseInt(ano) : null,
        placa, renavam, cor,
        km_atual: km ? parseInt(km) : null,
        combustivel,
        valor_diario: valorDiario ? parseFloat(valorDiario) : null,
        status: status || "Disponível",
        img: imagem, cliente: null
    });

    dom.formVeiculos.reset();
    fecharFormularioVeiculo();
    render();
}

/*
    CADASTRO DOS CLIENTES
*/

function cadastrarCliente() {
    const nome = document.getElementById("input-nome-cliente").value.trim();
    const cpf = document.getElementById("input-cpf-cliente").value.trim();
    const nascimento = document.getElementById("input-nascimento-cliente").value;
    const sexo = document.getElementById("input-sexo-cliente").value;
    const email = document.getElementById("input-email-cliente").value.trim();
    const telefone = document.getElementById("input-telefone-cliente").value.trim();
    const cep = document.getElementById("input-cep-cliente").value.trim();
    const estado = document.getElementById("input-estado-cliente").value.trim();
    const cidade = document.getElementById("input-cidade-cliente").value.trim();
    const bairro = document.getElementById("input-bairro-cliente").value.trim();
    const rua = document.getElementById("input-rua-cliente").value.trim();
    const numEnd = document.getElementById("input-numero-end-cliente").value.trim();
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
    CADASTRO DE FUNCIONÁRIOS
*/

function cadastrarFuncionario() {
    const nome = document.getElementById("input-nome-funcionario").value.trim();
    const cpf = document.getElementById("input-cpf-funcionario").value.trim();
    const nascimento = document.getElementById("input-nascimento-funcionario").value;
    const sexo = document.getElementById("input-sexo-funcionario").value;
    const email = document.getElementById("input-email-funcionario").value.trim();
    const telefone = document.getElementById("input-telefone1-funcionario").value.trim();
    const cargo = document.getElementById("input-cargo-funcionario").value.trim();
    const salario = document.getElementById("input-salario-funcionario").value;
    const admissao = document.getElementById("input-admissao-funcionario").value;

    if (!nome || !cpf || !cargo) return;

    funcionarios.push({
        id: funcionarios.length + 1,
        nome, cpf, nascimento, sexo, email, telefone, cargo,
        salario: salario ? parseFloat(salario) : null,
        admissao
    });

    dom.formFuncionarios.reset();
    fecharFormularioFuncionario();
    renderTabelaFuncionarios();
}

function deletarFuncionario(idx) {
    if (!confirm(`Tem certeza que deseja excluir o funcionário "${funcionarios[idx]?.nome}"?`)) return;
    funcionarios.splice(idx, 1);
    renderTabelaFuncionarios();
}

/*
    FORMULÁRIOS DE CADASTRO (Veículos / Clientes / Funcionários)
*/

function alternarFormulario(form, tabela) {
    const abrindo = form.style.display !== "block";
    form.style.display = abrindo ? "block" : "none";
    tabela.style.display = abrindo ? "none" : "block";
}

function fecharFormulario(form, tabela) {
    if (form) form.style.display = "none";
    if (tabela) tabela.style.display = "block";
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

function alternarFormularioFuncionario() {
    if (dom.formFuncionarios) alternarFormulario(dom.formFuncionarios, dom.tabelaFuncionarios);
}

function fecharFormularioFuncionario() {
    fecharFormulario(dom.formFuncionarios, dom.tabelaFuncionarios);
}

/*
    UTILITÁRIOS
*/

function formatarMoeda(valor) {
    return Number(valor).toLocaleString("pt-BR", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}

function formatarData(iso) {
    if (!iso) return "—";
    const [y, m, d] = iso.split("-");
    return `${d}/${m}/${y}`;
}
