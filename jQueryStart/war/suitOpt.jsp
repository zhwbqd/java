	<form action="/uc/my-measurements" method="post">
	<div class="measure_dashboard">
		<div class="measure_dashboard_content">
			<div class="measure_panel">
				<div class="measure_sidebar">
					<h2>Setup your measurement profile</h2>
					<p>Get started by filling in your height, weight, and age.</p>
				</div>
				<div class="measure_content">
					<p class="red">${measurementMsg}</p>
					<ul>
						<li><label>HEIGHT</label>
							<div class="measure_value">
								<input type="text" value="${(measurement.height)!''}" name="measure_height" class="txt_input" checkRule="required,double" filedName="height" msg="Hight must be a number!" msgField="measure_height_noti">&nbsp;IN
							</div>
							<p class="clearBoth"></p></li>

						<li><label>WEIGHT</label>
							<div class="measure_value">
								<input type="text" class="txt_input"  value="${(measurement.weight)!''}" name="measure_weight" checkRule="required,double" filedName="height" msg="Weight must be a number!" msgField="measure_height_noti"> KG
							</div>
							<p class="clearBoth"></p></li>


						<li><label>AGE</label>
							<div class="measure_value">
								<input type="text" class="txt_input" value="${(measurement.age)!''}" name="measure_age" checkRule="required,double" filedName="height" msg="Age must be a number!" msgField="measure_height_noti">
							</div>
							<p class="clearBoth"></p></li>


					</ul>
					<div class="red" id="measure_height_noti"></div>
					<p class="clearBoth"></p>
				</div>
			</div>
			
			<script type="text/javascript">
				function changePicAndApplyValue(src,value,target,field){
					jq("#"+target).attr("src",src);	
					jq("#"+field).val(value);
				};
			</script>
			
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Body Shape 1</h2>
					<p>Please tell us a little bit about your body shape.	</p>
				</div>
				<div class="measure_content">
					<div style="height: 300px;width: 400px;">
						<div class="attribute">
							<img src="/style/image/shoulders_normal.png" id="measure_shoulders_img">
							<h4>SHOULDERS</h4>
							<ul class="options">
								<li><input type="radio" name="measure_shoulders" ${(((measurement.shoulder)!'')=="Square Shoulders")?string('checked="checked"',"")} onclick="changePicAndApplyValue('/style/image/shoulders_square.png','Square Shoulders','measure_shoulders_img','measure_shoulders')" value="Square Shoulders"><label>Square Shoulders</label></li>
								<li><input type="radio" name="measure_shoulders" ${(((measurement.shoulder)!'')=="Normal Shoulders")?string('checked="checked"',"")} onclick="changePicAndApplyValue('/style/image/shoulders_normal.png','Normal Shoulders','measure_shoulders_img','measure_shoulders')" value="Normal Shoulders"><label>Normal Shoulders</label></li>
								<li><input type="radio" name="measure_shoulders" ${(((measurement.shoulder)!'')=="Sloping Shoulders")?string('checked="checked"',"")} onclick="changePicAndApplyValue('/style/image/shoulders_sloping.png','Sloping Shoulders','measure_shoulders_img','measure_shoulders')" value="Sloping Shoulders"><label >Sloping Shoulders</label></li>
							</ul>
							<input id="measure_shoulders" value="${(measurement.shoulder)!''}" type="hidden" name="measure_shoulders" checkRule="required" filedName="measure_shoulders" msg="please select a shoulders type!" msgField="measure_shoulders_noti">
							<p class="red" id="measure_shoulders_noti"></p>
						</div>
						<div class="attribute">
							<img src="/style/image/chest_muscular.png" id="measure_chest_ing">
							<h4>CHEST</h4>
							<ul class="options">
								<li><input type="radio" name="measure_chest" ${(((measurement.chest)!'')=="Muscular Chest")?string('checked="checked"',"")}  value="Muscular Chest" onclick="changePicAndApplyValue('/style/image/chest_muscular.png','Muscular Chest','measure_chest_ing','measure_chest')"><label>Muscular Chest</label></li>
								<li><input type="radio" name="measure_chest" ${(((measurement.chest)!'')=="Regular Chest")?string('checked="checked"',"")}  value="Regular Chest" onclick="changePicAndApplyValue('/style/image/chest_normal.png','Regular Chest','measure_chest_ing','measure_chest')"><label>Regular Chest</label></li>
								<li><input type="radio" name="measure_chest" ${(((measurement.chest)!'')=="Husky/Hefty Chest")?string('checked="checked"',"")} value="Husky/Hefty Chest" onclick="changePicAndApplyValue('/style/image/chest_husky_hefty.png','Husky/Hefty Chest','measure_chest_ing','measure_chest')"><label >Husky/Hefty Chest</label></li>
							</ul>
							<input id="measure_chest" type="hidden" name="measure_chest" checkRule="required" value="${(measurement.chest)!''}" filedName="measure_shoulders" msg="please select a chest type!" msgField="measure_chest_noti">
							<p class="red" id="measure_chest_noti"></p>
						</div>
					</div>
					<p class="clearBoth"></p>
				</div>
			</div>
			
			
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Body Shape 2</h2>
					<p>Please tell us a little bit about your body shape.	</p>
				</div>
				<div class="measure_content">
					<div style="height: 300px;width: 400px;">
						<div class="attribute">
							<img src="/style/image/stomach_average.png" id="measure_stomach_img">
							<h4>STOMACH</h4>
							<ul class="options">
								<li><input type="radio" name="measure_stomach" onclick="changePicAndApplyValue('/style/image/stomach_flat.png','Flat Stomach','measure_stomach_img','measure_stomach')" ${(((measurement.stomach)!'')=="Flat Stomach")?string('checked="checked"',"")} value="Flat Stomach"><label>Flat Stomach</label></li>
								<li><input type="radio" name="measure_stomach" onclick="changePicAndApplyValue('/style/image/stomach_average.png','Average Stomach','measure_stomach_img','measure_stomach')" ${(((measurement.sstomach)!'')=="Average Stomach")?string('checked="checked"',"")} value="Average Stomach"><label>Average Stomach</label></li>
								<li><input type="radio" name="measure_stomach" onclick="changePicAndApplyValue('/style/image/stomach_rounded.png','Rounded Stomach','measure_stomach_img','measure_stomach')" ${(((measurement.stomach)!'')=="Rounded Stomach")?string('checked="checked"',"")} value="Rounded Stomach"><label >Rounded Stomach</label></li>
							</ul>
							<input id="measure_stomach" type="hidden" name="measure_stomach" value="${(measurement.stomach)!''}" checkRule="required" filedName="measure_shoulders" msg="please select a chest type!" msgField="measure_stomach_noti">
							<p class="red" id="measure_stomach_noti"></p>
						</div>
						<div class="attribute">
							<img src="/style/image/posture_normal.png" id="measure_posture_img">
							<h4>POSTURE</h4>
							<ul class="options">
								<li><input type="radio" name="measure_posture" onclick="changePicAndApplyValue('/style/image/posture_flat.png','Flat Posture','measure_posture_img','measure_posture')" ${(((measurement.posture)!'')=="Flat Posture")?string('checked="checked"',"")} value="Flat Posture"><label>Flat Posture</label></li>
								<li><input type="radio" name="measure_posture" onclick="changePicAndApplyValue('/style/image/posture_normal.png','Normal Posture','measure_posture_img','measure_posture')" ${(((measurement.posture)!'')=="Normal Posture")?string('checked="checked"',"")} value="Normal Posture"><label>Normal Posture</label></li>
								<li><input type="radio" name="measure_posture" onclick="changePicAndApplyValue('/style/image/posture_hunched.png','Hunched Posture','measure_posture_img','measure_posture')" ${(((measurement.posture)!'')=="Hunched Posture")?string('checked="checked"',"")} value="Hunched Posture"><label >Hunched Posture</label></li>
							</ul>
							<input id="measure_posture" type="hidden" name="measure_posture" value="${(measurement.posture)!''}" id="measure_posture" checkRule="required" filedName="measure_shoulders" msg="please select a posture type!" msgField="measure_posture_noti">
							<p class="red" id="measure_posture_noti"></p>
						</div>
					</div>
					<p class="clearBoth"></p>
				</div>
			</div>
			
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Shirt Neck</h2>
					<ol class="tips">
						<li>Wrap the tape around your neck where your shirt collar would be.</li>
						<li>Imagine this is your actual shirt collar and adjust to your desired size.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" value="${(measurement.shirtNeck)!''}"  name="measure_shirt_neck" checkRule="required,double" filedName="Shirt Neck" msg="Shirt Neck must be a number!" msgField="measure_shirt_neck_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_shirt_neck_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Shirt-Neck.jpg" alt="suits measurement shirt neck" title="suits measurement shirt neck">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Jacket/Shirt Length</h2>
					<ol class="tips">
						<li>Pop Your Collar. Place the tape where the shoulder and neck seams meet..</li>
						<li>Measure straight down to the desired length, usually around the thumb joint. </li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input"  value="${(measurement.jacketShirtLenght)!''}" name="jacket_shirt_length" checkRule="required,double" filedName="Jacket/Shirt Length" msg="Shirt Neck must be a number!" msgField="jacket_shirt_length_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="jacket_shirt_length_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Jacket-Shirt-Length.jpg" alt="Suits measurement Jacket or Shirt Length" title="Suits measurement Jacket or Shirt Length">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Chest Size</h2>
					<ol class="tips">
						<li>Measure around the widest part of your chest, usually around the nipples.</li>
						<li>Leave room for 1 finger.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_chest_size" value="${(measurement.chestSize)!''}" checkRule="required,double" filedName="Chest Size" msg="Chest Size must be a number!" msgField="measure_chest_size_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_chest_size_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Chest-Size.jpg" alt="Suits measurement Chest Size" title="Suits measurement Chest Size">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Stomach Size</h2>
					<ol class="tips">
						<li>Measure around the widest part of your stomach, usually around the belly button.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_stomach_size" value="${(measurement.stomachSize)!''}" checkRule="required,double" filedName="Stomach Size" msg="Stomach Size must be a number!" msgField="measure_stomach_size_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_stomach_size_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Stomach-Size.jpg" alt="Suits measurement Stomach Size" title="Suits measurement Stomach Size">
					<p class="clearBoth"></p>
					
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Jacket Hips</h2>
					<ol class="tips">
						<li>Measure the widest part of your hips, usually where your bum peaks.</li>
						<li>Measure snug with room for one finger.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_jacket_hips" value="${(measurement.jacketHips)!''}" checkRule="required,double" filedName="Jacket Hips" msg="Jacket Hips must be a number!" msgField="measure_jacket_hips_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_jacket_hips_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Jacket-Hips.jpg" alt="Suits measurement Jacket Hips" title="Suits measurement Jacket Hips">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Shoulder Size</h2>
					<ol class="tips">
						<li>Place the tape where the shoulder and armhole seam meet on a well-fitting shirt.</li>
						<li>Measure straight across.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_shoulder_size" value="${(measurement.shoulderSize)!''}" checkRule="required,double" filedName="Shoulder Size" msg="Shoulder Size must be a number!" msgField="measure_shoulder_size_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_shoulder_size_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Shoulder-Size.jpg" alt="Suits measurement Shoulder Size" title="Suits measurement Shoulder Size">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Sleeve Length</h2>
					<ol class="tips">
						<li>Place the tape where the shoulder and armhole seam meet on a well-fitting shirt.</li>
						<li>Measure straight down your arm to your desired length.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_sleeve_length"  value="${(measurement.sleeveLength)!''}" checkRule="required,double" filedName="Sleeve Length" msg="Sleeve Length must be a number!" msgField="measure_sleeve_length_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_sleeve_length_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Sleeve-Length.jpg" alt="Suits measurement Sleeve Length" title="Suits measurement Sleeve Length">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Bicep Size</h2>
					<ol class="tips">
						<li>At the top of the armpit, measure the width of your bicep.</li>
						<li>Measure snug with room for one finger.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_bicep_size" value="${(measurement.bicepSize)!''}" checkRule="required,double" filedName="Bicep Size" msg="Bicep Size must be a number!" msgField="measure_bicep_size_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_bicep_size_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Bicep-Size.jpg" alt="Suits measurement Bicep Size" title="Suits measurement Bicep Size">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Wrist Size</h2>
					<ol class="tips">
						<li>Measure around the wrist bone.</li>
						<li>Leave room for one finger.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_wrist_size" value="${(measurement.wristSize)!''}" checkRule="required,double" filedName="Wrist Size" msg="Wrist Size must be a number!" msgField="measure_wrist_size_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_wrist_size_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Wrist-Size.jpg" alt="Suits measurement Wrist Sizee" title="Suits measurement Wrist Size">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Pants Length</h2>
					<ol class="tips">
						<li>Start from the top of the pants' waistband.</li>
						<li>Measure along the side pants seam to the bottom of your pants or roughly 1 to 1.5 inches from the ground.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_pants_length"  value="${(measurement.pantsLength)!''}"  checkRule="required,double" filedName="Pants Length" msg="Pants Length must be a number!" msgField="measure_pants_length_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_pants_length_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Pants-Length.jpg" alt="Suits measurement Pants Length" title="Suits measurement Pants Length">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Waist</h2>
					<ol class="tips">
						<li>Have the tape follow the top of your pants the whole way around.</li>
						<li>Imagine the tape is your actual pants' waist and adjust to your desired snugness.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_waist" value="${(measurement.waist)!''}" checkRule="required,double" filedName="Waist" msg="Waist must be a number!" msgField="measure_waist_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_waist_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Waist.jpg" alt="Suits measurement Waist" title="Suits measurement Waist">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Crotch</h2>
					<ol class="tips">
						<li>Place the tape at the middle of your waist.</li>
						<li>Follow the crotch seam through your legs, up to the front of the pants..</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_crotch" value="${(measurement.crotch)!''}" checkRule="required,double" filedName="Crotch" msg="Crotch must be a number!" msgField="measure_crotch_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_crotch_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Crotch.jpg" alt="Suits measurement Crotch" title="Suits measurement Crotch">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Thigh Size</h2>
					<ol class="tips">
						<li>Starting at the top of your inseam, measure around your thigh.</li>
						<li>Measure snug with room for one finger.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_thigh_size" value="${(measurement.thighSize)!''}" checkRule="required,double" filedName="Thigh Size" msg="Thigh Size must be a number!" msgField="measure_thigh_size_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_thigh_size_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Thigh-Size.jpg" alt="Suits measurement Thigh Size" title="Suits measurement Thigh Size">
					<p class="clearBoth"></p>
				</div>
			</div>
			<div class="measure_panel" style="display: none">
				<div class="measure_sidebar">
					<h2>Knee Size</h2>
					<ol class="tips">
						<li>Measure around your knee cap, snug, with room for one finger.</li>
					</ol>
				</div>
				<div class="measure_field">
					<input type="text" class="measure_input" name="measure_knee_size" value="${(measurement.kneeSize)!''}"  checkRule="required,double" filedName="Knee Size" msg="Knee Size must be a number!" msgField="measure_knee_size_noti"><SPAN style="font-size: 22px;font-weight: bold;">&nbsp;IN </SPAN>
					<p id="measure_knee_size_noti" class="red"></p>
				</div>
				<div class="measure_content">
					<img src="/style/image/suit_measure_img/Knee-Size.jpg" alt="Suits measurement Knee Size" title="Suits measurement Knee Size">
					<p class="clearBoth"></p>
					<p class="clearBoth"></p>
				</div>
			</div>
			<p class="clearBoth"></p>
		</div>
		<div class="measure_navigation">
			<div class="measure_buttons">
				<input type="button" class="button_01" value="<         PREVIOUS">
				<input type="button" class="button_01" value="NEXT             >">
				<input type="submit" class="button_01" value="FINISH           >" onclick="return jq('.measure_dashboard').fieldCheck();">
			</div>
			<div class="measure_controller">
				<div class="circle_hint" style="display: none;">BODY SHAPE</div>
				<img class="pagination_active_m" src="/style/image/1x1.png" title="Hight&Weight">
				<img class="pagination_m" src="/style/image/1x1.png" title="Body Shape 1">
				<img class="pagination_m" src="/style/image/1x1.png" title="Body Shape 2">
				<img class="pagination_m" src="/style/image/1x1.png" title="Shirt Neck">
				<img class="pagination_m" src="/style/image/1x1.png" title="Jacket/Shirt Length">
				<img class="pagination_m" src="/style/image/1x1.png" title="Chest Size">
				<img class="pagination_m" src="/style/image/1x1.png" title="Stomach Size">
				<img class="pagination_m" src="/style/image/1x1.png" title="Jacket Hips">
				<img class="pagination_m" src="/style/image/1x1.png" title="Shoulder Size">
				<img class="pagination_m" src="/style/image/1x1.png" title="Sleeve Length">
				<img class="pagination_m" src="/style/image/1x1.png" title="Bicep Size">
				<img class="pagination_m" src="/style/image/1x1.png" title="Wrist Size">
				<img class="pagination_m" src="/style/image/1x1.png" title="Pants Length">
				<img class="pagination_m" src="/style/image/1x1.png" title="Waist">
				<img class="pagination_m" src="/style/image/1x1.png" title="Crotch">
				<img class="pagination_m" src="/style/image/1x1.png" title="Thigh Size">
				<img class="pagination_m" src="/style/image/1x1.png" title="Knee Size">
			</div>
		</div>
	</div>
	</form>
	<script type="text/javascript">
		jq(".measure_dashboard").slider();
	</script>