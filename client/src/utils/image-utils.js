import axios from "axios";

export default {
  convertRawDataToSrc(image) {
    return 'data:image/jpeg;base64,' + image;
  },
  convertSrcToRawData() {

  },
  setProfileData(id, file) {
    let formData = new FormData();
    formData.append('file', file);

    return axios.post(`/user/${id}/set-image`, formData,
      {
        headers: {
          'Content-Type': `multipart/form-data;`
        }
      })
  }
}
