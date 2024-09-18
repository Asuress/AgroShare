import axios from "@/axios";
import App from "@/App.vue";

export default {
  addPublication(publication) {
    console.log(publication);
    axios.post('/publications/add', {
      title: publication.title,
      description: publication.description,
      price: publication.price,
      category: publication.selected,
      publicationType: publication.isRent === true ? "R" : "S",
      publisher: publication.username
    }).then(response => {
      console.log(response.data);
      // callback(response.data);
    })
  },
  getCategories(callback) {
    axios.get('/publications/categories').then(response => {
      console.log(response.data);
      callback(response.data);
    })
  },
  getPublicationsByUserId(id) {
    return axios.get(`/publications/find/user/${id}`);
  },
  getPublicationInfoById(id) {
    return axios.get(`/publications/get/${id}`)
  }
}
