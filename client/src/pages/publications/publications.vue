<template>
  <v-container flex
               app
  >

    <v-layout>
      <v-card
        class="flex-0-0-100 black--text ml-auto my-custom-background mb-4"
      >
        <v-row>
          <v-col md="8"
          >
            <v-text-field
              prepend-icon="mdi-magnify"
              hide-details
              single-line
              class="flex-1-0-1 ml-5 mt-1"
              bg-color="grey"
              color="grey"
              v-model="searchField"
              @keydown.enter="search"
            ></v-text-field>

          </v-col>
          <v-col md="auto"
          >
            <v-card-actions class="d-flex justify-end"
            >
              <v-btn
                @click="search"
                class="justify-end flex-1-1 ma-2"
              >
                Поиск
              </v-btn>
              <v-btn
                @click="addPublish"
                class="justify-end flex-1-1 ma-2"
              >
                Добавить объявление
              </v-btn>
            </v-card-actions>
          </v-col>
        </v-row>
      </v-card>
    </v-layout>

    <v-layout>
      <v-col md="2">

      </v-col>
      <v-row class="list__publications">
        <v-col md="4" v-for="item in publications" :key="item.id">
          <v-card @click="openPublication(item.id)"
                  max-height="500"
                  class="bg-grey-darken-1"
          >

            <v-img :src="item.image"
                   height="250"
                   color="black"
            >
              <template v-slot:placeholder>
                <div class="d-flex align-center justify-center fill-height">
                  <v-progress-circular
                    color="grey-lighten-4"
                    indeterminate
                  ></v-progress-circular>
                </div>
              </template>
            </v-img> <!-- С помощью v-img добавляем изображение карточки -->

            <v-card-title class="text-wrap"> <!-- Заголовок заведения -->
              <h3 class="text-h4">{{ item.title }}</h3>
            </v-card-title>

            <v-card-text> <!-- Описание заведения -->
              <p class="text-h5">{{ item.price }} руб.</p>
            </v-card-text>

            <v-card-text class="pe-0 pt-0"> <!-- Описание заведения -->
              <p class="text-body-1">{{ item.description }}</p>
            </v-card-text>

          </v-card>
        </v-col>
      </v-row>

    </v-layout>
  </v-container>
</template>

<script>
import UserHelper from "@/utils/user-helper";
import axios from "axios";
import ImageUtils from "@/utils/image-utils";
import router from "@/router";
import PublicationHelper from "@/utils/publications/publication-helper";

export default {
  name: "publications",
  data() {
    return {
      publications: null,
      searchField: null,
      categories: []
    }
  },
  methods: {
    addPublish() {
      if (UserHelper.isAuthorized()) {
        console.log("Authorized");
        this.$router.push('/add-publication').catch(e => {
          console.log("error", e);
        });
      } else {
        this.$router.push("/login")
      }
      console.log("end");
    },
    search() {
      console.log("this.searchField", this.searchField)
      if (this.searchField !== null && this.searchField !== '') {
        axios.get("/publications/find", {
          params: {
            title: this.searchField
          }
        }).then(response => {
          console.log(response.data)
          this.publications = response.data;
        })
      } else {
        axios.get("/publications/list/last").then(response => {
          console.log("response:", response);
          this.publications = response.data;
        }).catch(error => {
          console.log("error", error)
        });
      }
    },
    openPublication(id) {
      console.log("publication id", id);
      console.log("isAuthorized:", UserHelper.isAuthorized());
      // if (UserHelper.isAuthorized()) {
      this.$router.push(`/publications/publication/${id}`);
      // } else {
      //   this.$router.push(`/registration`);
      // }
    },
  },
  async mounted() {
    console.log(localStorage.getItem('access_token'));
    console.log("userId:", this.$route.params.userId);
    console.log("this.route.name:", this.$route.name);

    if (!!this.$route.params.userId) {
      PublicationHelper.getPublicationsByUserId(this.$route.params.userId).then(response => {
        this.publications = response.data;
        // this.publications.forEach(item => {
        //   item.image = ImageUtils.convertRawDataToSrc(item.image)
        // })
      }).catch(error => {
        console.log(error.response);
        console.log(error)
        if (error.response.status === 401) {
          UserHelper.unauthorize();
          this.$router.go(this.$router.route);
        }
      });
    } else {
      await axios.get("/publications/list/last").then(response => {
        console.log("response:", response);
        this.publications = response.data;
        // this.publications.forEach(item => {
        //   console.log("item:", item)
        //   item.image = ImageUtils.convertRawDataToSrc(item.image)
        // });
        console.log('publications converted', this.publications);
      }).catch(error => {
        console.log(error.response);
        console.log(error)
        if (error.response.status === 401) {
          UserHelper.unauthorize();
          this.$router.go("/");
        }
      });
    }
  },
  created() {
    // this.categories = [
    //   {
    //     id: 1,
    //     title: "Category1"
    //   },
    //   {
    //     id: 2,
    //     title: "Category2"
    //   }
    // ];
    console.log("categories", this.categories)
  }
}
</script>

<style scoped>
.my-custom-background {
  background-color: #2E7D32; /* ваш кастомный цвет */
}
</style>
