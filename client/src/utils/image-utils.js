import axios from "axios";

export default {
  convertRawDataToSrc(image) {
    return 'data:image/jpeg;base64,' + image;
  },
  convertSrcToRawData() {

  },
  setProfileData(id, avatarPreview) {
    let data = new FormData();
    data.append('image', avatarPreview);

    axios.post(`/user/${id}/set-image`, avatarPreview,
      {
      'Content-Type': `multipart/form-data; boundary=${avatarPreview._boundary}`
    })
  }
}
