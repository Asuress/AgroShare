<template>
  <v-container flex app>
    <v-layout>
      <v-card justify-center
              class="elevation-12 flex-0-0-100 black--text"
              color="grey-lighten-3">
        <v-toolbar dark color="teal-darken-2">
          <v-toolbar-title>Добавление объявления</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field
              name="title"
              label="Заголовок"
              type="text"
              v-model="publication.title"
            ></v-text-field>
            <v-row>
              <v-col>
                <v-autocomplete
                  label="Категория"
                  v-model="publication.selected"
                  :items="categories"
                  item-title=""
                >

                </v-autocomplete>
              </v-col>
              <v-col>
                <v-text-field
                  id="price"
                  name="price"
                  label="Цена"
                  type="text"
                  prefix="Р"
                  v-model="publication.price"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-textarea
                  id="description"
                  name="description"
                  label="Описание"
                  type="text"
                  v-model="publication.description"
                >

                </v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-radio-group inline
                             v-model="publication.isRent"
              >
                <v-radio label="Аренда"
                         value="true"
                >

                </v-radio>
                <v-radio label="Продажа"
                         value="false"
                >

                </v-radio>
              </v-radio-group>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary"
                 @click="addPublication"
          >
            Добавить
          </v-btn>
          <v-btn color="primary"
                 @click="cancel"
          >
            Отменить
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-layout>
  </v-container>
</template>

<script>
import router from "@/router";
import PublicationHelper from "@/utils/publications/publication-helper";

export default {
  name: "add-publication",
  data() {
    return {
      publication: {
        title: '',
        description: '',
        price: '',
        selected: '',
        isRent: true,
        username: '',
      },
      categories: [],
    }
  },
  methods: {
    cancel() {
      router.go(-1);
    },
    addPublication() {
      PublicationHelper.addPublication(this.publication);
    }
  },
  created() {
    PublicationHelper.getCategories((data) => {
      console.log("data", data);
      this.categories = data.map(item => {
        return item.categoryName;
      });
    });
    this.publication.username = localStorage.getItem('username');
  }
}
</script>

<style scoped lang="sass">

</style>
