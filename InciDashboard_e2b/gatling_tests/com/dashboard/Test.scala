package com.dashboard

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Test extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_41 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity",
		"Origin" -> "http://localhost:8090")

    val uri1 = "http://maps.googleapis.com"
    val uri3 = "http://maps.gstatic.com/mapfiles"
    val uri4 = "http://fonts.gstatic.com/s/roboto/v18"
    val uri5 = "http://fonts.googleapis.com/css"

	val scn = scenario("Test")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(5)
		.exec(http("request_2")
			.post("/login")
			.headers(headers_0)
			.formParam("username", "pedro@example.com")
			.formParam("password", "1234")
			.resources(http("request_3")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(4)
		.exec(http("request_4")
			.get("/user/showMap/43.3616142/-5.8506767")
			.headers(headers_0)
			.resources(http("request_5")
			.get(uri1 + "/maps/api/js?key=AIzaSyBKrZ_YQOFfUy6ZJSDQbPIdkq5LBhtJqaI"),
            http("request_6")
			.get(uri1 + "/maps-api-v3/api/js/32/10/common.js"),
            http("request_7")
			.get(uri1 + "/maps-api-v3/api/js/32/10/marker.js"),
            http("request_8")
			.get(uri1 + "/maps-api-v3/api/js/32/10/map.js"),
            http("request_9")
			.get(uri1 + "/maps-api-v3/api/js/32/10/util.js"),
            http("request_10")
			.get(uri1 + "/maps-api-v3/api/js/32/10/onion.js"),
            http("request_11")
			.get(uri1 + "/maps/api/js/ViewportInfoService.GetViewportInfo?1m6&1m2&1d43.34498364001043&2d-5.098703230253136&2m2&1d43.3788366706675&2d-4.902137245792801&2u15&4sen-US&5e0&6sm%40418000000&7b0&8e0&callback=_xdc_._yhy0jj&token=66595"),
            http("request_12")
			.get(uri3 + "/openhand_8_8.cur"),
            http("request_13")
			.get(uri3 + "/transparent.png"),
            http("request_14")
			.get(uri3 + "/api-3/images/spotlight-poi2_hdpi.png"),
            http("request_15")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15927!3i11995!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=82978"),
            http("request_16")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15928!3i11995!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=72888"),
            http("request_17")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15928!3i11994!4i256!2m3!1e0!2sm!3i418116012!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=96010"),
            http("request_18")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15929!3i11994!4i256!2m3!1e0!2sm!3i418116012!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=85920"),
            http("request_19")
			.get(uri1 + "/maps/vt?pb=!1m4!1m3!1i15!2i15925!3i11994!1m4!1m3!1i15!2i15925!3i11995!1m4!1m3!1i15!2i15926!3i11994!1m4!1m3!1i15!2i15926!3i11995!1m4!1m3!1i15!2i15927!3i11994!1m4!1m3!1i15!2i15927!3i11995!1m4!1m3!1i15!2i15925!3i11996!1m4!1m3!1i15!2i15926!3i11996!1m4!1m3!1i15!2i15927!3i11996!1m4!1m3!1i15!2i15928!3i11994!1m4!1m3!1i15!2i15928!3i11995!1m4!1m3!1i15!2i15929!3i11994!1m4!1m3!1i15!2i15929!3i11995!1m4!1m3!1i15!2i15930!3i11994!1m4!1m3!1i15!2i15930!3i11995!1m4!1m3!1i15!2i15931!3i11994!1m4!1m3!1i15!2i15931!3i11995!1m4!1m3!1i15!2i15928!3i11996!1m4!1m3!1i15!2i15929!3i11996!1m4!1m3!1i15!2i15930!3i11996!1m4!1m3!1i15!2i15931!3i11996!2m3!1e0!2sm!3i418118065!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e3!12m1!5b1!23i1301875&callback=_xdc_._tqz730&token=12491"),
            http("request_20")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15929!3i11995!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=62798"),
            http("request_21")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15926!3i11994!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=12550"),
            http("request_22")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15926!3i11995!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=93068"),
            http("request_23")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15927!3i11994!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=2460"),
            http("request_24")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15929!3i11996!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=12245"),
            http("request_25")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15928!3i11996!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=22335"),
            http("request_26")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15930!3i11994!4i256!2m3!1e0!2sm!3i418104930!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=129874"),
            http("request_27")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15931!3i11994!4i256!2m3!1e0!2sm!3i418104606!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=7274"),
            http("request_28")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15926!3i11996!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=42515"),
            http("request_29")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15930!3i11995!4i256!2m3!1e0!2sm!3i418090851!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=56866"),
            http("request_30")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15930!3i11996!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=89888"),
            http("request_31")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15925!3i11995!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=103158"),
            http("request_32")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15925!3i11996!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=52605"),
            http("request_33")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15925!3i11994!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=22640"),
            http("request_34")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15927!3i11996!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=32425"),
            http("request_35")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15931!3i11995!4i256!2m3!1e0!2sm!3i418090851!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=46776"),
            http("request_36")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i15!2i15931!3i11996!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=79798"),
            http("request_37")
			.get(uri1 + "/maps-api-v3/api/js/32/10/controls.js"),
            http("request_38")
			.get(uri1 + "/maps/api/js/AuthenticationService.Authenticate?1shttp%3A%2F%2Flocalhost%3A8090%2Fuser%2FshowMap%2F43.3616142%2F-5.8506767&4sAIzaSyBKrZ_YQOFfUy6ZJSDQbPIdkq5LBhtJqaI&callback=_xdc_._r0seio&token=25132"),
            http("request_39")
			.get(uri5 + "?family=Roboto:300,400,500,700")
			.headers(headers_1),
            http("request_40")
			.get(uri3 + "/api-3/images/google4_hdpi.png"),
            http("request_41")
			.get(uri4 + "/KFOlCnqEu92Fr1MmEU9fBBc4.woff2")
			.headers(headers_41),
            http("request_42")
			.get(uri4 + "/KFOmCnqEu92Fr1Mu4mxK.woff2")
			.headers(headers_41),
            http("request_43")
			.get(uri3 + "/api-3/images/tmapctrl_hdpi.png"),
            http("request_44")
			.get(uri3 + "/api-3/images/mapcnt6.png"),
            http("request_45")
			.get(uri3 + "/api-3/images/tmapctrl4_hdpi.png"),
            http("request_46")
			.get(uri3 + "/mv/imgs8.png"),
            http("request_47")
			.get(uri3 + "/api-3/images/sv9.png"),
            http("request_48")
			.get(uri3 + "/api-3/images/cb_scout5_hdpi.png")))
		.pause(1)
		.exec(http("request_49")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31855!3i23991!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=112678")
			.resources(http("request_50")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31854!3i23991!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=122768"),
            http("request_51")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31853!3i23991!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=1787"),
            http("request_52")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31853!3i23990!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=52340"),
            http("request_53")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31853!3i23989!4i256!2m3!1e0!2sm!3i418108962!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=43613"),
            http("request_54")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31857!3i23989!4i256!2m3!1e0!2sm!3i418116012!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=35636"),
            http("request_55")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31856!3i23990!4i256!2m3!1e0!2sm!3i418108962!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=93327"),
            http("request_56")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31854!3i23990!4i256!2m3!1e0!2sm!3i418108962!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=113507"),
            http("request_57")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31855!3i23990!4i256!2m3!1e0!2sm!3i418108962!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=103417"),
            http("request_58")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31854!3i23989!4i256!2m3!1e0!2sm!3i418108962!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=33523"),
            http("request_59")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31857!3i23990!4i256!2m3!1e0!2sm!3i418090851!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=59476"),
            http("request_60")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31856!3i23989!4i256!2m3!1e0!2sm!3i418116012!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=45726"),
            http("request_61")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31855!3i23989!4i256!2m3!1e0!2sm!3i418116012!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=55816"),
            http("request_62")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31856!3i23991!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=102588"),
            http("request_63")
			.get(uri1 + "/maps/api/js/ViewportInfoService.GetViewportInfo?1m6&1m2&1d43.35407356706157&2d-5.0614878966104015&2m2&1d43.37099979411604&2d-4.963206578229574&2u16&4sen-US&5e0&6sm%40418000000&7b0&8e0&callback=_xdc_._yf2h03&token=43216"),
            http("request_64")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31857!3i23991!4i256!2m3!1e0!2sm!3i418089028!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=122557"),
            http("request_65")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31852!3i23991!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=11877"),
            http("request_66")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31852!3i23990!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=62430"),
            http("request_67")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31852!3i23989!4i256!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=113517"),
            http("request_68")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31858!3i23989!4i256!2m3!1e0!2sm!3i418112934!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=109250"),
            http("request_69")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31858!3i23990!4i256!2m3!1e0!2sm!3i418112934!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=58163"),
            http("request_70")
			.get(uri1 + "/maps/vt?pb=!1m5!1m4!1i16!2i31858!3i23991!4i256!2m3!1e0!2sm!3i418089028!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e0!5m1!5f2!23i1301875&token=112467"),
            http("request_71")
			.get(uri1 + "/maps/vt?pb=!1m4!1m3!1i15!2i15925!3i11994!1m4!1m3!1i15!2i15925!3i11995!1m4!1m3!1i15!2i15926!3i11994!1m4!1m3!1i15!2i15926!3i11995!1m4!1m3!1i15!2i15927!3i11994!1m4!1m3!1i15!2i15927!3i11995!1m4!1m3!1i15!2i15925!3i11996!1m4!1m3!1i15!2i15926!3i11996!1m4!1m3!1i15!2i15927!3i11996!1m4!1m3!1i15!2i15928!3i11994!1m4!1m3!1i15!2i15928!3i11995!1m4!1m3!1i15!2i15929!3i11994!1m4!1m3!1i15!2i15929!3i11995!1m4!1m3!1i15!2i15930!3i11994!1m4!1m3!1i15!2i15930!3i11995!1m4!1m3!1i15!2i15931!3i11994!1m4!1m3!1i15!2i15931!3i11995!1m4!1m3!1i15!2i15928!3i11996!1m4!1m3!1i15!2i15929!3i11996!1m4!1m3!1i15!2i15930!3i11996!1m4!1m3!1i15!2i15931!3i11996!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e3!12m1!5b1!23i1301875&callback=_xdc_._zguppy&token=67390"),
            http("request_72")
			.get(uri1 + "/maps/vt?pb=!1m4!1m3!1i16!2i31852!3i23989!1m4!1m3!1i16!2i31853!3i23989!1m4!1m3!1i16!2i31852!3i23990!1m4!1m3!1i16!2i31852!3i23991!1m4!1m3!1i16!2i31853!3i23990!1m4!1m3!1i16!2i31853!3i23991!1m4!1m3!1i16!2i31854!3i23989!1m4!1m3!1i16!2i31855!3i23989!1m4!1m3!1i16!2i31854!3i23990!1m4!1m3!1i16!2i31854!3i23991!1m4!1m3!1i16!2i31855!3i23990!1m4!1m3!1i16!2i31855!3i23991!1m4!1m3!1i16!2i31856!3i23989!1m4!1m3!1i16!2i31857!3i23989!1m4!1m3!1i16!2i31856!3i23990!1m4!1m3!1i16!2i31856!3i23991!1m4!1m3!1i16!2i31857!3i23990!1m4!1m3!1i16!2i31857!3i23991!1m4!1m3!1i16!2i31858!3i23989!1m4!1m3!1i16!2i31858!3i23990!1m4!1m3!1i16!2i31858!3i23991!2m3!1e0!2sm!3i418117980!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e3!12m1!5b1!23i1301875&callback=_xdc_._7e6g9s&token=27220"),
            http("request_73")
			.get(uri1 + "/maps/api/js/ViewportInfoService.GetViewportInfo?1m6&1m2&1d43.34493905771544&2d-5.0986979860040265&2m2&1d43.37879205355312&2d-4.902132203720726&2u15&4sen-US&5e0&6sm%40418000000&7b0&8e0&callback=_xdc_._ls9ozg&token=110042"),
            http("request_74")
			.get(uri1 + "/maps/vt?pb=!1m4!1m3!1i16!2i31852!3i23989!1m4!1m3!1i16!2i31853!3i23989!1m4!1m3!1i16!2i31852!3i23990!1m4!1m3!1i16!2i31852!3i23991!1m4!1m3!1i16!2i31853!3i23990!1m4!1m3!1i16!2i31853!3i23991!1m4!1m3!1i16!2i31854!3i23989!1m4!1m3!1i16!2i31855!3i23989!1m4!1m3!1i16!2i31854!3i23990!1m4!1m3!1i16!2i31854!3i23991!1m4!1m3!1i16!2i31855!3i23990!1m4!1m3!1i16!2i31855!3i23991!1m4!1m3!1i16!2i31856!3i23989!1m4!1m3!1i16!2i31857!3i23989!1m4!1m3!1i16!2i31856!3i23990!1m4!1m3!1i16!2i31856!3i23991!1m4!1m3!1i16!2i31857!3i23990!1m4!1m3!1i16!2i31857!3i23991!1m4!1m3!1i16!2i31858!3i23989!1m4!1m3!1i16!2i31858!3i23990!1m4!1m3!1i16!2i31858!3i23991!2m3!1e0!2sm!3i418118065!3m9!2sen-US!3sUS!5e18!12m1!1e68!12m3!1e37!2m1!1ssmartmaps!4e3!12m1!5b1!23i1301875&callback=_xdc_._1oaxmu&token=7292"),
            http("request_75")
			.get("/user/listIncidencias")
			.headers(headers_0),
            http("request_76")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_77")
			.get("/user/changeStatus/1")
			.headers(headers_0)
			.resources(http("request_78")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_79")
			.post("/user/changeStatus/1")
			.headers(headers_0)
			.formParam("estado", "CERRADA")
			.resources(http("request_80")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_81")
			.get("/user/listComments/3")
			.headers(headers_0)
			.resources(http("request_82")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_83")
			.get("/user/addComment/3")
			.headers(headers_0),
            http("request_84")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(6)
		.exec(http("request_85")
			.post("/user/addComment/3")
			.headers(headers_0)
			.formParam("texto", " Comentario de gatling")
			.resources(http("request_86")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_87")
			.get("/user/listIncidencias")
			.headers(headers_0)
			.resources(http("request_88")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_89")
			.get("/incidence/1")
			.headers(headers_0)
			.resources(http("request_90")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_91")
			.get("/user/listIncidencias")
			.headers(headers_0)
			.resources(http("request_92")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_93")
			.get("/user/changeStatus/1")
			.headers(headers_0),
            http("request_94")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_95")
			.post("/user/changeStatus/1")
			.headers(headers_0)
			.formParam("estado", "ABIERTA")
			.resources(http("request_96")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(4)
		.exec(http("request_97")
			.get("/user/listComments/1")
			.headers(headers_0)
			.resources(http("request_98")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_99")
			.get("/user/addComment/1")
			.headers(headers_0)
			.resources(http("request_100")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(4)
		.exec(http("request_101")
			.post("/user/addComment/1")
			.headers(headers_0)
			.formParam("texto", " Peligro!!")
			.resources(http("request_102")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_103")
			.get("/user/listIncidencias")
			.headers(headers_0)
			.resources(http("request_104")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_105")
			.get("/logout")
			.headers(headers_0),
            http("request_106")
			.get("/css/custom.css")
			.headers(headers_1)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}