import axios from "@/axios";
import App from "@/App.vue";

export default {
  addPublication(publication) {
    axios.post('/publications/add', {
      title: publication.title,
      description: publication.description,
      price: publication.price,
      category: publication.category,
      publicationType: publication.publicationType,
      publisherId: publication.publisherId,
      image: publication.image
    }).then(response => {
      // callback(response.data);
    })
  },
  getCategories(callback) {
    axios.get('/publications/categories').then(response => {
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
