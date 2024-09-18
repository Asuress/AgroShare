<template>
  <v-container flex app>
    <v-layout>
      <v-card justify-center
              class="elevation-12 flex-0-0-100 black--text"
              color="grey-lighten-3">
        <v-toolbar class="my-custom-background">
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
              <v-col class="v-col-md-2">
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
              </v-col>
              <v-col class="v-col-md-2">
                <v-btn icon="attach" class="my-custom-background-button mb-2" block rounded
                       @click="triggerPublicationImageUpload">Добавить фото
                </v-btn>
                <input type="file" ref="publicationImageRef" @change="onPublicationImageChange" accept="image/*"
                       style="display: none;"/>
              </v-col>
              <v-col class="v-col-md-8">
                <!--              <v-avatar size="100" class="mx-auto mb-4">-->
                <v-img :src="publication.publicationImage"/>
                <!--              </v-avatar>-->
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="my-custom-background-button red--text"
                 @click="addPublication"
          >
            Добавить
          </v-btn>
          <v-btn class="my-custom-background-button"
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
        publicationImage: null
      },
      categories: [],
    }
  },
  methods: {
    cancel() {
      router.go(-1);
    },
    addPublication() {
      console.log("new publication data:", this.publication);
      PublicationHelper.addPublication(this.publication);
    },
    triggerPublicationImageUpload() {
      this.$refs.publicationImageRef.click();
    },
    // Изменение аватара
    onPublicationImageChange(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.publication.publicationImage = e.target.result; // Показываем превью аватара
          console.log("publication image:", this.publication.publicationImage);
        };
        reader.readAsDataURL(file);
      }
    },
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

<style scoped>
.my-custom-background {
  background-color: #2E7D32; /* ваш кастомный цвет */
}
</style>
