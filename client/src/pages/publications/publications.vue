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

            <v-img
              height="250"
              :src="require(getImagePath(item))"

            ></v-img> <!-- С помощью v-img добавляем изображение карточки -->

            <v-card-title class="text-wrap"> <!-- Заголовок заведения -->
              <h3 class="text-h4">{{ item.title }}</h3>
            </v-card-title>

            <v-card-text> <!-- Описание заведения -->
              <p class="text-h5">{{ item.price }} руб.</p>
            </v-card-text>

            <v-card-text> <!-- Описание заведения -->
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

export default {
  name: "publications",
  data() {
    return {
      publications: null,
      searchField: null,
      categories: [
        {
          id: 1,
          title: "Category1",
          children: [
            { id: 2, title: 'Calendar : app' },
            { id: 3, title: 'Chrome : app' },
            { id: 4, title: 'Webstorm : app' },
          ],
        },
        {
          id: 2,
          title: "Category2"
        }
      ]
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
      if (UserHelper.isAuthorized()) {
        this.$router.push(`/publications/publication/${id}`);
      } else {
        this.$router.push(`/registration`);
      }
    },
    getImagePath(item) {
      console.log("item", item)
      console.log("item", item.image)
      console.log(new URL(item.image, import.meta.url).href)
      return item.image;
      // switch (item.title) {
      //   case "МТЗ-82":
      //     console.log("item title МТЗ", "@/assets/МТЗ.jpg")
      //     return "@/assets/МТЗ.jpg"
      //   default:
      //     console.log("item title default", item.title)
      //     return "";
      // }
    }
  },
  mounted() {
    console.log(localStorage.getItem('access_token'));
    axios.get("/publications/list/last").then(response => {
      console.log("response:", response);
      this.publications = response.data;
    }).catch(error => {
      console.log("error", error)
    });
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
