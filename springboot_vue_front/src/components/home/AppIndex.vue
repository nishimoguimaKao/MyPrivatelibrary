<template>
  <div>
    <el-carousel :interval="5000" arrow="always" height="880px">
      <el-carousel-item v-for="item in items" :key="item">
        <img v-bind:src="item" class="image">
      </el-carousel-item>
    </el-carousel>
    <el-tooltip placement="bottom">
      <div slot="content">
        高校公益图书分享平台<br/>
        为同学提供更多的高质量图书<br/>
        图书共享<br/>
        让思想展翅飞翔<br/>
        .<br/>
        .<br/>
        .<br/>
        .<br/>
      </div>
      <el-button class="el-icon-info"> &nbsp;使用说明</el-button>
    </el-tooltip>
  </div>
</template>

<script>
export default {
  name: "AppIndex",
  data() {
    return {
      items: {
        src1: '../../static/image_1.jpg',
        src2: '../../static/image_2.jpg',
        src3: '../../static/image_3.jpg',
        src4: '../../static/image_4.jpg'
      }
    }
  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #ffffff;
  font-size: 18px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #ffffff;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #ffffff;
}
</style>
