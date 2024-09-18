const state = {
  username: '',
  token: '',
  id: null
}

const getters = {
  username: state => state.username,
  token: state => state.token,
  id: state => state.id,
}

const actions = {
  setUsername(context, username) {
    context.commit('setUsername', username);
  },
  setToken(context, token) {
    context.commit('setToken', token);
  },
  setId(context, id) {
    context.commit('setId', id);
  },
}

const mutations = {
  setUsername(state, username) {
    state.username = username;
  },
  setToken(state, token) {
    state.token = token;
  },
  setId(state, id) {
    state.id = id;
  },
}

export default {
  state,
  getters,
  actions,
  mutations
}
