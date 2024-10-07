<template>
  <v-container>
    <v-card outlined>
      <v-card-title>
        Основная карточка объявления
      </v-card-title>
      <v-row>
        <!-- Изображение товара -->
        <v-col cols="5">
          <v-img :src="ad.image"
                 alt="Image of ad"
                 aspect-ratio="1"
                 height="400"
                 cover
                 contain></v-img>
          <v-btn icon color="red" @click="toggleFavorite">
            <v-icon>{{ isFavorite ? 'mdi-heart' : 'mdi-heart-outline' }}</v-icon>
          </v-btn>
        </v-col>

        <!-- Описание товара -->
        <v-col cols="7">
          <v-card-title class="headline" wrap>{{ ad.title }}</v-card-title>
          <v-card-subtitle class="category">
            Категория: {{ ad.category }}
          </v-card-subtitle>
          <p class="location">
            <v-icon left>mdi-map-marker</v-icon>
            {{ ad.location }}
          </p>
          <v-divider></v-divider>

          <v-card-text>
            <p class="price" v-if="!!ad.price">{{ formatPrice(ad.price) }} руб.</p>
            <p>{{ ad.description }}</p>
            <v-divider></v-divider>
            <!--            <p class="meta-info">Дата публикации: {{ ad.date }}</p>-->
            <!--            <p class="meta-info">Просмотры: {{ ad.views }}</p>-->
          </v-card-text>

          <!-- Кнопки действий -->
          <v-card-actions>
            <v-btn class="my-custom-background-button" disabled @click="viewDetails">Подробнее</v-btn>
            <v-btn class="my-custom-background-button-primary" @click="showContact">Показать телефон</v-btn>
          </v-card-actions>

          <!-- Информация о продавце -->
          <v-divider></v-divider>
          <v-card-subtitle class="seller-info">
            Продавец: {{ ad.email }}
          </v-card-subtitle>
          <v-btn class="my-custom-background-button" @click="viewSellerAds">Все объявления продавца</v-btn>
        </v-col>
        <v-col>
          <app-calendar v-if="ad.publicationType === 'R'">
          </app-calendar>
        </v-col>
      </v-row>

      <!-- Модальное окно для показа телефона -->
      <v-dialog v-model="contactDialog" persistent max-width="400">
        <v-card>
          <v-card-title>Контактная информация</v-card-title>
          <v-card-text>
            <p><strong>Телефон:</strong> {{ ad.contact.phone }}</p>
          </v-card-text>
          <v-card-actions>
            <v-btn class="my-custom-background-button" @click="contactDialog = false">Закрыть</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card>
  </v-container>
</template>

<script>
import UserHelper from "@/utils/user-helper";
import PublicationHelper from "@/utils/publications/publication-helper";
import {th} from "vuetify/locale";
import AppCalendar from "@/components/app-calendar.vue";

export default {
  components: {AppCalendar},
  props: {},
  data() {
    return {
      isFavorite: false,
      contactDialog: false,
      ad: {
        title: null,
        category: null,
        location: null,
        image: null,
        description: null,
        price: null,
        contact: {phone: "8 (999) 123-45-67"},
        seller: null,
        date: null,
        views: null,
        id: this.$route.params.id,
      },
    };
  },
  methods: {
    formatPrice(value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
    },
    viewDetails() {
      this.$router.push({name: 'adDetails', params: {id: this.ad.id}});
    },
    showContact() {
      this.contactDialog = true;
    },
    viewSellerAds() {
      this.$router.push(`/publications/user/${id}`);
    },
    toggleFavorite() {
      this.isFavorite = !this.isFavorite;
    },
    viewSimilarAd(id) {
      this.$router.push({name: 'adDetails', params: {id}});
    },
    async fetchData() {
      this.loading = true; // Устанавливаем флаг загрузки
      try {
        console.log("params.id", this.$route.params.id);
        await PublicationHelper.getPublicationInfoById(this.$route.params.id).then(response => {
          console.log("publication data", response.data);
          this.ad = response.data;
        });
      } catch (error) {
        console.error('Ошибка при загрузке данных:', error);
      } finally {
        this.loading = false; // Сбрасываем флаг загрузки
      }
    },
  },
  created() {
    this.fetchData();
  }
};
</script>

<style scoped>
.ad-card {
  margin-bottom: 40px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.headline {
  font-weight: bold;
  font-size: 1.5rem;
}

.category, .location, .meta-info, .seller-info {
  font-size: 1.2rem;
  color: gray;
}

.price {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 15px;
}

v-img {
  border-radius: 8px;
  object-fit: contain;
}

v-btn {
  margin-top: 20px;
}

.similar-ads {
  margin-top: 40px;
}

.similar-ad-card {
  padding: 10px;
  text-align: center;
}

.similar-ad-card .headline {
  font-size: 1.2rem;
  font-weight: bold;
}

.similar-ad-card .price {
  font-size: 1.2rem;
  color: gray;
}

.similar-ad-card v-btn {
  margin-top: 10px;
}

</style>
