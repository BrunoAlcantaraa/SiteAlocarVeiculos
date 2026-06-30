/*
    DOM
*/

const pages = {
    "pag-dashboard": document.getElementById("pag-dashboard"),
    "pag-alocacao": document.getElementById("pag-alocacao"),
    "pag-cadastro-veiculos": document.getElementById("pag-cadastro-veiculos"),
    "pag-cadastro-clientes": document.getElementById("pag-cadastro-clientes")
};

const dom = {
    loginScreen: document.getElementById("login-screen"),
    layout: document.getElementById("layout"),
    containerVeiculos: document.getElementById("container-veiculos"),
    msgSemVeiculos: document.getElementById("msg-sem-veiculos"),
    actionBarTitle: document.getElementById("action-bar-title"),
    actionBarActions: document.getElementById("action-bar-actions"),
    tbodyVeiculos: document.getElementById("tbody-veiculos"),
    tabelaVeiculos: document.getElementById("tabela-veiculos"),
    formVeiculos: document.getElementById("form-cadastrar-veiculo"),
    tbodyClientes: document.getElementById("tbody-clientes"),
    tabelaClientes: document.getElementById("tabela-clientes"),
    formClientes: document.getElementById("form-cadastrar-clientes"),
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
        status: "Disponível", cor: "Cinza Metálico", combustivel: "Gasolina",
        km_atual: 32000, valor_diario: 200.00, cliente: null
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
    { id: 1, nome: "Ana Silva", cargo: "Atendente" },
    { id: 2, nome: "Carlos Souza", cargo: "Gerente" },
    { id: 3, nome: "Mariana Costa", cargo: "Atendente" }
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

    // Futuramente: substituir por chamada à API de autenticação
    // const autenticado = await api.autenticar({ usuario, senha });
    // if (!autenticado) { erro.textContent = "Usuário ou senha inválidos."; return; }

    erro.textContent = "";
    dom.loginScreen.style.display = "none";
    dom.layout.style.display = "flex";
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
    const lista = dados !== undefined ? dados : veiculos;
    dom.containerVeiculos.innerHTML = "";
    dom.msgSemVeiculos.style.display = lista.length === 0 ? "block" : "none";
    lista.forEach(v => adicionarCardVeiculo(v));
}

function renderDashboard() {
    const alocados = veiculos.filter(v => v.cliente !== null);
    const disponiveis = veiculos.filter(v => !v.cliente && v.status === "Disponível");

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
    renderCardsVeiculos(buscarVeiculos(termo));
}

/*
    NAVEGAÇÃO
*/

function mostrarPagina(paginaId) {
    // Volta para grade ao sair da página de alocação
    voltarParaVeiculos();

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
    CARDS DE VEÍCULO
*/

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

function veiculoDisponivel(v) {
    return v.cliente === null && v.status === "Disponível";
}

function adicionarCardVeiculo(veiculo) {
    const idx = veiculos.indexOf(veiculo);
    const status = getStatusVeiculo(veiculo);
    const disponivel = veiculoDisponivel(veiculo);
    const preco = veiculo.valor_diario
        ? `R$ ${formatarMoeda(veiculo.valor_diario)}/dia`
        : "—";

    const card = document.createElement("div");
    card.classList.add("card-veiculo");
    if (!disponivel) card.classList.add("card-indisponivel");

    let msgBloqueio = "";
    if (status === "Alugado") msgBloqueio = `<p class="card-indisponivel-msg">Veículo alugado</p>`;
    if (status === "Em manutenção") msgBloqueio = `<p class="card-indisponivel-msg">Em manutenção</p>`;

    const btnHtml = disponivel
        ? `<button class="btn-selecionar" onclick="selecionarVeiculo(${idx})">Selecionar</button>`
        : `<button class="btn-bloqueado" disabled>Indisponível</button>`;

    card.innerHTML = `
        <div class="card-img-wrapper">
            <img src="${veiculo.img || ""}" alt="${veiculo.nome || "Veículo"}">
            <span class="status-badge ${getBadgeClass(status)}">${status}</span>
        </div>
        <h3>${veiculo.nome || "—"}</h3>
        <p class="card-marca">${veiculo.marca || "—"}${veiculo.ano ? " · " + veiculo.ano : ""}</p>
        <p class="card-placa">${veiculo.placa || "—"}</p>
        <p class="card-preco">${preco}</p>
        ${msgBloqueio}
        ${btnHtml}
    `;

    dom.containerVeiculos.appendChild(card);
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
    // Preenche detalhes do veículo
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

    // Preenche KM mínimo de saída
    const kmInput = document.getElementById("alocar-km-saida");
    kmInput.value = v.km_atual || "";
    kmInput.min = v.km_atual || 0;

    // Data mínima de saída = hoje
    const hoje = new Date().toISOString().split("T")[0];
    document.getElementById("alocar-data-saida").min = hoje;
    document.getElementById("alocar-data-retorno").min = hoje;

    // Popula selects
    popularSelectClientes();
    popularSelectFuncionarios();
    // popularSelectEstados();

    // Limpa e calcula resumo inicial
    limparFormAlocacao();
    calcularResumo();

    // Exibe painel de formulário, oculta grade
    document.getElementById("alocacao-selecao").style.display = "none";
    document.getElementById("alocacao-form-wrapper").style.display = "block";
}

function voltarParaVeiculos() {
    const wrapper = document.getElementById("alocacao-form-wrapper");
    const selecao = document.getElementById("alocacao-selecao");
    if (wrapper) wrapper.style.display = "none";
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

// function popularSelectEstados() {                                     Retirar
//     const sel = document.getElementById("alocar-estado");
//     sel.innerHTML = '<option value="">Selecione o estado</option>';
//     Object.keys(estadosCidades).sort().forEach(uf => {
//         const opt = document.createElement("option");
//         opt.value       = uf;
//         opt.textContent = uf;
//         sel.appendChild(opt);
//     });
// }

// function carregarCidades() {                                           Retirar
//     const uf       = document.getElementById("alocar-estado").value;
//     const selCidade = document.getElementById("alocar-cidade");
//     selCidade.innerHTML = '<option value="">Selecione a cidade</option>';

//     if (!uf || !estadosCidades[uf]) {
//         selCidade.disabled = true;
//         return;
//     }

//     estadosCidades[uf].forEach(cidade => {
//         const opt = document.createElement("option");
//         opt.value       = cidade;
//         opt.textContent = cidade;
//         selCidade.appendChild(opt);
//     });
//     selCidade.disabled = false;
// }

/*
    CÁLCULO DO RESUMO
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
        // Datas no formato YYYY-MM-DD — parse sem fuso horário
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
    VALIDAÇÃO DO FORMULÁRIO
*/

function limparFormAlocacao() {
    const form = document.getElementById("form-alocacao");
    if (form) form.reset();

    const cidade = document.getElementById("alocar-cidade");
    if (cidade) cidade.disabled = true;

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
        // { id: "alocar-estado", erroId: "erro-estado", msg: "Selecione o estado." },
        // { id: "alocar-cidade", erroId: "erro-cidade", msg: "Selecione a cidade." },
        // { id: "alocar-logradouro", erroId: "erro-logradouro", msg: "Informe o logradouro." },
        // { id: "alocar-numero", erroId: "erro-numero", msg: "Informe o número." },
        { id: "alocar-status", erroId: "erro-status", msg: "Selecione o status da locação." },
        { id: "alocar-pagamento", erroId: "erro-pagamento", msg: "Selecione a forma de pagamento." },
    ];

    // Limpa erros anteriores
    campos.forEach(c => {
        const el = document.getElementById(c.id);
        const erro = document.getElementById(c.erroId);
        if (el) el.classList.remove("campo-invalido");
        if (erro) erro.textContent = "";
    });

    // Campos obrigatórios
    campos.forEach(c => {
        const el = document.getElementById(c.id);
        if (!el || !el.value.trim()) {
            if (el) el.classList.add("campo-invalido");
            const erro = document.getElementById(c.erroId);
            if (erro) erro.textContent = c.msg;
            valido = false;
        }
    });

    // Validação de datas
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

    // Validação de KM
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
        // Scroll para o topo do formulário
        document.getElementById("notif-alocacao").scrollIntoView({ behavior: "smooth", block: "nearest" });
        return;
    }

    const v = veiculos[indexVeiculoSelecionado];
    const clienteSel = clientes[document.getElementById("alocar-cliente").value];

    // Atualiza o veículo (futuramente: via API)
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
    FORMULÁRIOS DE CADASTRO (Veículos / Clientes)
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
    const [y, m, d] = iso.split("-");
    return `${d}/${m}/${y}`;
}
